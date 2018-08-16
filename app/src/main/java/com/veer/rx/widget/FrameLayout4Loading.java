/**
 *
 */
package com.veer.rx.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.veer.rx.R;

/***
 * 加载页面
 * @author Veer
 * @email  276412667@qq.com
 * @date   18/7/3
 */
public class FrameLayout4Loading extends FrameLayout {

	/*** 没有数据显示 */
	public static final int ViewType_EmptyView = -1;
	/*** 请求中 */
	public static final int ViewType_Loading = 0;
	/*** 加载异常, 在无合适的异常类型使用的情况下使用此异常 */
	public static final int ViewType_DefaultException = 1;
	/*** 无网络 */
	public static final int ViewType_NoNetwork = 4;
	/*** 网络超时 */
	public static final int ViewType_Timeout = 5;
	/*** 数据异常, 服务器返回数据有误(比如反序列化失败) */
	public static final int ViewType_ServerException = 3;

	/** 其他错误 */
	public static final int EXP_DEFAULT_FAIL = 10001;
	/** 无法连接到网络，请检查网络配置 */
	public static final int EXP_NETWORK_NOTAVAILABLE = 90001;
	/** 超时了，请您重试 */
	public static final int EXP_REQUEST_TIMEOUT = 90003;
	/** 服务出错啦，请稍后重试 */
	public static final int EXP_SERVICE_FAIL = 90004;
	/** 服务调用出错，但不带重试按钮只显示信息 */
	public static final int EXP_SERVICE_FAIL_INFO = 150101;

	private static Animation loadingAnim;

	private SparseArray<Integer> defaultLayout = new SparseArray<Integer>(7);
	public SparseArray<View> cachedLayout = new SparseArray<View>(7);

	private LayoutInflater mInflater;

	private OnClickListener mRefreshClickListener;



	private ImageView icon;
	private TextView tip;
	private TextView subTip;
	private boolean viewUp = false;

	private LottieAnimationView mLottieAnimationView;

	private Context mContext;

	public boolean isViewUp() {
		return viewUp;
	}

	public void setViewUp(boolean viewUp) {
		this.viewUp = viewUp;
	}


	public FrameLayout4Loading(Context context) {
		super(context);
		init(context, null);
	}

	public FrameLayout4Loading(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);

	}

	public FrameLayout4Loading(Context context, AttributeSet attrs,
							   int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {

		this.mContext = context;

		if (isInEditMode())
			return;

		if (loadingAnim==null){
			loadingAnim = AnimationUtils.loadAnimation(context, R.anim.round_loading);
		}

		mInflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);

		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs,
					R.styleable.FrameLayout4Loading);
			if (a != null) {
				setDefaultView(ViewType_Loading, a.getResourceId(
						R.styleable.FrameLayout4Loading_loadingView,
						R.layout.common_loading_indicator));

				setDefaultView(ViewType_NoNetwork, a.getResourceId(
						R.styleable.FrameLayout4Loading_loadingView,
						R.layout.common_error_default_style));

				setDefaultView(ViewType_Timeout, a.getResourceId(
						R.styleable.FrameLayout4Loading_loadingView,
						R.layout.common_error_default_style));

				setDefaultView(ViewType_DefaultException, a.getResourceId(
						R.styleable.FrameLayout4Loading_loadingView,
						R.layout.common_error_default_style));

				setDefaultView(ViewType_ServerException, a.getResourceId(
						R.styleable.FrameLayout4Loading_defaultExceptionView,
						R.layout.common_error_default_style));

				setDefaultView(ViewType_EmptyView,
						R.layout.common_empty_view);

				a.recycle();
			}
		}
	}

	public void setDefaultView(int viewType, int resLayout) {
		defaultLayout.put(viewType, resLayout);
	}

	public int getDefaultViewResId(int viewType) {
		return defaultLayout.get(viewType);
	}

	public void showView(int viewType, Drawable background) {
		int count = defaultLayout.size();
		for (int i = 0; i < count; i++) {
			int key = defaultLayout.keyAt(i);
			if (key == viewType) {
				doShowView(key, background);
			} else {
				hideView(key, background);
			}
		}
	}

	private void hideView(int viewType, Drawable background) {
		View view = cachedLayout.get(viewType);

		if (view == null)
			return;
		if (background != null) {
			view.setBackground(background);
		}
		view.setVisibility(GONE);
	}
	/**
	 * 没有数据时显示
	 * @param background  图片
	 * @param msg  描述文字
	 */
	public void doShowNoData(Drawable background, String msg) {
		doShowNoData(background,msg,null,null);
	}


	/**
	 * 没有数据时显示
	 * @param background  图片
	 * @param msg  描述文字
	 * @param btnTxt  按钮文字
	 * @param click   点击事件
	 */
	public void doShowNoData(Drawable background, String msg, String btnTxt, OnClickListener click) {
		int resLayoutId = defaultLayout.get(ViewType_EmptyView);
		if (resLayoutId <= 0)
			throw new IllegalStateException("layout is not set for " + ViewType_EmptyView);


		View view = cachedLayout.get(ViewType_EmptyView);

		if (view == null || click != null) {
			view = mInflater.inflate(resLayoutId, null);
			try {
				ImageView image = (ImageView)view.findViewById(R.id.empty_icon_iv);

				image.setBackground(background);

				TextView empText = (TextView) view
						.findViewById(R.id.empty_tip_tv);
				empText.setText(msg);

				if (click!=null){
					TextView empty_btn = (TextView) view.findViewById(R.id.empty_btn);
					empty_btn.setVisibility(View.VISIBLE);
					empty_btn.setOnClickListener(click);
					empty_btn.setText(btnTxt);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			cachedLayout.put(ViewType_EmptyView, view);
			addView(view, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
					Gravity.CENTER));
			initView(view, ViewType_EmptyView);
			initListener(view);
		}

		view.setVisibility(VISIBLE);
		view.bringToFront();
	}



	private void doShowView(int viewType, Drawable background) {
		int resLayoutId = defaultLayout.get(viewType);
		if (resLayoutId <= 0)
			throw new IllegalStateException("layout is not set for " + viewType);

		View view = cachedLayout.get(viewType);

		if (view == null) {
			view = mInflater.inflate(resLayoutId, null);
			try {
				TextView errorText = (TextView) view.findViewById(R.id.loading_error_text);
				if ( viewType == ViewType_Timeout
						|| viewType == ViewType_NoNetwork
						) {
					errorText.setText("亲爱的，您的网络开小差了哦");

				}else if(viewType == ViewType_DefaultException
						||viewType == ViewType_ServerException){
					errorText.setText("系统繁忙，请稍后再试！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (background != null) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
					view.setBackground(background);
				} else {
					view.setBackgroundDrawable(background);
				}
			}
			cachedLayout.put(viewType, view);
			addView(view, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
					Gravity.CENTER));
			initView(view, viewType);
			initListener(view);
		}

		view.setVisibility(VISIBLE);
		view.bringToFront();
	}

	private void initView(View v, int viewType) {

		if (ViewType_EmptyView == viewType) {
			icon = (ImageView) v.findViewById(R.id.empty_icon_iv);
			tip = (TextView) v.findViewById(R.id.empty_tip_tv);
			subTip = (TextView) v.findViewById(R.id.empty_sub_tip_tv);
		} else if (ViewType_Loading == viewType) {
			mLottieAnimationView = v.findViewById(R.id.loading_iv);
			//设置加载速度
			mLottieAnimationView.setSpeed(10);
		}
	}

	private void initListener(View view) {
		View refreshBtn = view.findViewById(R.id.loading_refreash_btn);
		if (refreshBtn != null && mRefreshClickListener != null) {
			refreshBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mRefreshClickListener.onClick(v);
				}
			});
		}
	}

	/**
	 * 显示加载动态图
	 */
	public void showLoadingView() {
		showLoadingView(null);
	}
	/**
	 * 显示加载动态图
	 * @param backgroundId  背景图ID
	 */
	public void showLoadingView(int backgroundId) {
		showLoadingView(getResources().getDrawable(backgroundId));
	}
	/**
	 * 显示加载动态图
	 * @param background  背景图
	 */
	public void showLoadingView(Drawable background) {
		showView(ViewType_Loading, background);
	}
	/**
	 * 隐藏加载动态图
	 */
	public void hideLoadingView() {
		hideLoadingView(null);
	}

	public void hideLoadingView(int backgroundId) {
		hideLoadingView(getResources().getDrawable(backgroundId));
	}

	public void hideLoadingView(Drawable background) {
		hideView(ViewType_Loading, background);
	}


	public void showDefaultExceptionView() {
		showExceptionView(EXP_DEFAULT_FAIL);
	}
	/**
	 * 显示空页面
	 */
	public void showEmptyView() {
		showEmptyView(null);
	}

	public void showEmptyView(int backgroundId) {
		showEmptyView(getResources().getDrawable(backgroundId));
	}

	public void showEmptyView(Drawable background) {
		showView(ViewType_EmptyView, background);
	}

	/**
	 * 显示异常页面
	 * @param errorCode
	 */
	public void showExceptionView(int errorCode) {
		showView(getViewTypeByErrorCode(errorCode), null);
	}

	public void showExceptionView(int errorCode, int backgroundId) {
		showExceptionView(errorCode, getResources().getDrawable(backgroundId));
	}

	public void showExceptionView(int errorCode, Drawable background) {
		showView(getViewTypeByErrorCode(errorCode), background);
	}

	public void setExceptionViewIconVisibility(int visibility) {
		View view = cachedLayout.get(ViewType_DefaultException);
		view.findViewById(R.id.listview_error_pic).setVisibility(visibility);
	}

	public <T> FrameLayout4Loading setEmptyViewIcon(T obj) {
		if (obj == null || icon == null)
			return null;
		if (obj instanceof Integer)
			icon.setImageResource((Integer) obj);
		else
			ViewCompat.setBackground(icon, (Drawable) obj);
		return this;
	}

	public <T> FrameLayout4Loading setEmptyViewTip(T obj) {
		if (obj == null || tip == null)
			return null;
		if (obj instanceof Integer)
			((TextView) tip).setText(getResources().getString((Integer) obj));
		else if (obj instanceof CharSequence)
			((TextView) tip).setText((CharSequence) obj);
		return this;
	}

	public <T> FrameLayout4Loading setEmptyViewTip(T obj, T obj1) {
		if (obj == null || tip == null)
			return null;

		if (obj instanceof Integer)
			((TextView) tip).setText(getResources().getString((Integer) obj));
		else if (obj instanceof CharSequence)
			((TextView) tip).setText((CharSequence) obj);

		if (obj1 == null || subTip == null) {
			return null;
		}

		subTip.setVisibility(View.VISIBLE);
		if (obj1 instanceof Integer)
			((TextView) subTip).setText(getResources()
					.getString((Integer) obj1));
		else if (obj instanceof CharSequence)
			((TextView) subTip).setText((CharSequence) obj1);

		return this;
	}

	public void hideAllMask() {
		hideAllMask(null);
	}

	public void hideAllMask(int backgroundId) {
		hideAllMask(getResources().getDrawable(backgroundId));
	}

	public void hideAllMask(Drawable background) {
		int count = cachedLayout.size();
		for (int i = 0; i < count; i++) {
			int key = cachedLayout.keyAt(i);
			hideView(key, background);
		}
	}

	private static int getViewTypeByErrorCode(int errorCode) {
		switch (errorCode) {
			case EXP_NETWORK_NOTAVAILABLE :
				return ViewType_NoNetwork;

			case EXP_REQUEST_TIMEOUT :
				return ViewType_Timeout;

			case EXP_SERVICE_FAIL :
				return ViewType_ServerException;

			case EXP_DEFAULT_FAIL :
			case EXP_SERVICE_FAIL_INFO :
			default :
				return ViewType_DefaultException;
		}
	}

	public void setRefreashClickListener(OnClickListener listener) {
		mRefreshClickListener = listener;
	}

}
