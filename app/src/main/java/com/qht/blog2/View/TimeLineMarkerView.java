package com.qht.blog2.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.qht.blog2.R;

/**
 * Created by QHT on 2017-04-14.
 *
 */
public class TimeLineMarkerView extends View {

    private int mMarkerSize = 24; //圆点大小
    private int mLineSize = 2; //线段粗细
    private Drawable mBeginLine; //上面线段颜色或者图片
    private Drawable mEndLine; //下面线段颜色或者图片
    private Drawable mMarkerDrawable;//圆点颜色或者图片

    public TimeLineMarkerView(Context context) {
        this(context,null);
    }

    public TimeLineMarkerView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TimeLineMarkerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TimeLineMarker);

        mMarkerSize = a.getDimensionPixelSize(
                R.styleable.TimeLineMarker_markerSize,
                mMarkerSize);

        mLineSize = a.getDimensionPixelSize(
                R.styleable.TimeLineMarker_lineSize,
                mLineSize);

        mBeginLine = a.getDrawable(
                R.styleable.TimeLineMarker_beginLine);

        mEndLine = a.getDrawable(
                R.styleable.TimeLineMarker_endLine);

        mMarkerDrawable = a.getDrawable(
                R.styleable.TimeLineMarker_marker);

        a.recycle();

//        if (mBeginLine != null)
//            mBeginLine.setCallback(this);
//
//        if (mEndLine != null)
//            mEndLine.setCallback(this);
//
//        if (mMarkerDrawable != null)
//            mMarkerDrawable.setCallback(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w=getPaddingLeft()+getPaddingRight();
        int h=getPaddingTop()+getPaddingBottom();

        if(mMarkerDrawable!=null){
            w=w+mMarkerSize;
            h=h+mMarkerSize;
        }
        w=Math.max(w,getMeasuredWidth());
        h=Math.max(h,getMeasuredHeight());

        int widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
        int heightSize = resolveSizeAndState(h, widthMeasureSpec, 0);

        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBeginLine != null) {
            mBeginLine.draw(canvas);
        }

        if (mEndLine != null) {
            mEndLine.draw(canvas);
        }

        if (mMarkerDrawable != null) {
            mMarkerDrawable.draw(canvas);
        }

        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initDrawableSize();
    }

     //考虑两种情况，空或者不空
    private void initDrawableSize() {
        int pLeft=getPaddingLeft();
        int pRight=getPaddingRight();
        int pTop=getPaddingTop();
        int pBottom=getPaddingBottom();

        int width=getWidth();
        int height=getHeight();

        int cWidth=width-pLeft-pRight;
        int cHeight=height-pTop-pBottom;

        Rect bounds;

        //重新分配大小
        if(mMarkerDrawable!=null){
            int marksize=Math.min(Math.min(cWidth,cHeight),mMarkerSize);
            mMarkerDrawable.setBounds(pLeft,pTop,pLeft+marksize,pTop+marksize);

            bounds=mMarkerDrawable.getBounds();

        }else{
            //如果drawable为空，我们自己去设置View的默认大小
            //我们没有设置两条线挨住，因为我们需要这个bounds.top去设置上面线段的bottom
            bounds=new Rect(pLeft,pTop,pLeft+cWidth,pTop+cHeight);
        }

        int halfLine=mLineSize >> 1;
        int lineLeft=bounds.centerX()-halfLine;


        if(mBeginLine!=null){
            mBeginLine.setBounds(lineLeft,0,lineLeft+mLineSize,bounds.top);
        }

        if(mEndLine!=null){
            mEndLine.setBounds(lineLeft,bounds.bottom,lineLeft+mLineSize,height);
        }
    }
       //下来提供几个方法。以供代码动态设置
    public void setLineSize(int  linesize) {
        if (this.mLineSize != linesize) {
            mLineSize = linesize;
            initDrawableSize();
            invalidate();
        }
    }

    public void setMarkerSize(int markerSize) {
        if (this.mMarkerSize != markerSize) {
            mMarkerSize = markerSize;
            initDrawableSize();
            invalidate();
        }
    }

    public void setBeginLine(Drawable beginLine) {
        if (this.mBeginLine != beginLine) {
            this.mBeginLine = beginLine;
            if (mBeginLine != null) {
                mBeginLine.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    public void setEndLine(Drawable endLine) {
        if (this.mEndLine != endLine) {
            this.mEndLine = endLine;
            if (mEndLine != null) {
                mEndLine.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    public void setMarkerDrawable(Drawable markerDrawable) {
        if (this.mMarkerDrawable != markerDrawable) {
            this.mMarkerDrawable = markerDrawable;
            if (mMarkerDrawable != null) {
                mMarkerDrawable.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    }
