package com.fly.drag;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.text.style.SuperscriptSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class DragLayout extends FrameLayout {
	
	private ViewGroup mMainContent;
	private ViewGroup mLeftContent ;
	private ViewDragHelper mDragHelper;
	private int mRange;
	private int mHeight;
	private int mWidth;
	
	public DragLayout(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	public DragLayout(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	//a、初始化操作
		mDragHelper = ViewDragHelper.create(this, mCallback);
		
	}

	ViewDragHelper.Callback mCallback = new Callback() {
		
		//1、根据返回结果决定当前child是否可以拖拽
		public boolean tryCaptureView(View arg0, int arg1) {
			
			return true;
		}
		//2、根据建议值修正要移动的位置
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			 return left;			
		}			
		//当capterChild被捕获时，会调用
		public void onViewCaptured(View capturedChild, int activePointerId) {
			     super.onViewCaptured(capturedChild, activePointerId);						
		}
		
		public int getViewHorizontalDragRange(View child) {
			
			return computeHorizontalScrollRange();
		};
				
		
	};

	
    //传递触摸事件
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return mDragHelper.shouldInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		try{
			
			mDragHelper.processTouchEvent(event);
		}catch(Exception e){		
			e.printStackTrace();
		}
		
		
		//返回true，持续接收事件
		return true;
		
		
		
	}
	
	@Override
	protected void onFinishInflate() {	
		super.onFinishInflate();
		
		//Github
		
		 mLeftContent = (ViewGroup) getChildAt(0);
		 mMainContent = (ViewGroup) getChildAt(1);
		
	}
	
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		mHeight =getMeasuredHeight();
		mWidth = getMeasuredWidth();
		
		 mRange = (int) (mWidth*0.6f);
	}
	
}
