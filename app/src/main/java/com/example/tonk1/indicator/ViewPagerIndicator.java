package com.example.tonk1.indicator;

        import android.content.Context;
        import android.content.Intent;
        import android.content.res.TypedArray;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.CornerPathEffect;
        import android.graphics.Paint;
        import android.graphics.Path;
        import android.support.v4.view.ViewPager;
        import android.util.AttributeSet;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.LinearLayout;

/**
 * Created by Yangxj on 2016/11/1.
 */

public class ViewPagerIndicator extends LinearLayout {
    private Paint mPaint;
    private Path mPath;
    private int mTriangleWidth;
    private int mTriangleHeight;
    private static final float  RADIO_TRIANGLE_WIDTH = 1/6F;
    private int mInitTransLationX;
    private int mTranslationX;
    private int tab_visible_count;

    public void main(String[] args){
        System.out.print(String.valueOf(System.currentTimeMillis()).substring(5, 9) + String.valueOf(Double.valueOf(Math.random()).intValue()*100));
    }

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#66ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        tab_visible_count = a.getInteger(R.styleable.ViewPagerIndicator_visible_tab_count, 3);
        if(tab_visible_count <= 0){
            tab_visible_count = 3;
        }
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w/tab_visible_count*RADIO_TRIANGLE_WIDTH);
        mInitTransLationX = w/tab_visible_count/2 - mTriangleWidth/2;
        initTriangle();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mInitTransLationX + mTranslationX, getHeight() + 5);
        canvas.drawPath(mPath,mPaint);


        canvas.restore();

        super.dispatchDraw(canvas);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int count = getChildCount();
        for(int i = 0; i < count; i ++){
            View view = getChildAt(i);
            LinearLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth()/tab_visible_count;
            view.setLayoutParams(lp);


        }
    }

    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;

    }

    private void initTriangle() {
        mTriangleHeight = mTriangleWidth/2;

        mPath = new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth/2, -mTriangleHeight);
        mPath.close();
    }

    public void setscroll(int position, float offset) {
        int tabWidth = getWidth()/tab_visible_count;
        mTranslationX = (int) (tabWidth * (offset + position));
        if (getChildCount() > tab_visible_count && position >= tab_visible_count - 2 && offset > 0){
            this.scrollTo((position - (tab_visible_count - 2))*tabWidth + (int)(tabWidth*offset), 0);
        }
        invalidate();
    }
}
