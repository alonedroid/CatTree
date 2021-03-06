package com.yoitai.cattree;

// ゲームの制御
public class Game {
	// テクスチャ番号
	public static final int TEXNO_BACK     = 0;	// 背景
	public static final int TEXNO_BUILDING = 1;	// 建物
	public static final int TEXNO_TBLOCK = 2;		// 障害物上
	public static final int TEXNO_BBLOCK = 3;		// 障害物下
	public static final int TEXNO_CHAR0   = 4;		// キャラクタ0
	public static final int TEXNO_CHAR1   = 5;		// キャラクタ1
	public static final int TEXNO_CHAR2   = 6;		// キャラクタ2
	public static final int TEXNO_ENEMY0  = 7;		// 敵キャラクタ0
	public static final int TEXNO_ENEMY1  = 8;		// 敵キャラクタ1
	public static final int TEXNO_BULLET  = 9;		// 弾

	// メンバー変数
	MainActivity mMainActivity;
	MainRenderer mMyRenderer;
	Input mInput;

	// 内容が変化するゲーム情報
	long mFrameNo;
	Stage mStage;
	UFO mUFO;

	// コンストラクタ
	public Game()
	{
		mStage = new Stage();
		mUFO = new UFO();
	}

	// viewの設定
	public void setView(MainView _view)
	{
		mMainActivity = _view.getMainActivity();
		mMyRenderer = _view.mMainRenderer;
		mInput = _view.mInput;

		mStage.setView(_view);

		mUFO.setView(_view);
		mUFO.setInput(mInput);
		mUFO.setStage(mStage);
	}

	// ゲーム初期化処理(MyRendererからonSurfaceCreated時に実行されます)
	public void gameInitialize()
	{
		// 各テクスチャ読み込み
		mMyRenderer.getTexture(TEXNO_BACK).readTexture(mMainActivity		, "bg.png"				,  22, 510,  0.0f,   0.0f,   0.0f,    0.0f);
		mMyRenderer.getTexture(TEXNO_BUILDING).readTexture(mMainActivity	, "building.png"		, 202, 158,  0.0f, 158.0f,   0.0f, -158.0f);
		mMyRenderer.getTexture(TEXNO_TBLOCK).readTexture(mMainActivity	, "tpole.png"			, 109, 512,  0.0f, 498.0f,   0.0f, -498.0f);
		mMyRenderer.getTexture(TEXNO_BBLOCK).readTexture(mMainActivity	, "bpole.png"			, 109, 512,  0.0f,  16.0f,   0.0f,  -16.0f);
		mMyRenderer.getTexture(TEXNO_CHAR0).readTexture(mMainActivity		, "ufo_default.png"	, 173,  90, 84.0f,  57.0f, -84.0f,  -57.0f);
		mMyRenderer.getTexture(TEXNO_CHAR1).readTexture(mMainActivity		, "ufo_fire1.png"		, 173, 138, 84.0f,  57.0f, -84.0f,  -57.0f);
		mMyRenderer.getTexture(TEXNO_CHAR2).readTexture(mMainActivity		, "ufo_fire2.png"		, 173, 138, 84.0f,  57.0f, -84.0f,  -57.0f);
		mMyRenderer.getTexture(TEXNO_ENEMY0).readTexture(mMainActivity	, "bird1.png"			,  86,  79, 44.0f,  40.0f, -44.0f,  -40.0f);
		mMyRenderer.getTexture(TEXNO_ENEMY1).readTexture(mMainActivity, "bird2.png", 90, 79, 44.0f, 40.0f, -44.0f, -40.0f);
		mMyRenderer.getTexture(TEXNO_BULLET).readTexture(mMainActivity, "bullet.png", 26, 18, 13.0f, 8.0f, -13.0f, 8.0f);
	}

	// 毎フレーム処理(FPS毎にMainThreadから呼ばれます)
	public void frameFunction()
	{
		// == 以下 フレーム処理 ==
		// ステージフレーム処理
		mStage.frameFunction();

		// UFOのフレーム処理
		mUFO.frameFunction();

		mFrameNo++;

		// == 以下 描画処理 ==
		// スクリーンクリア
		DrawParams params;
		params = mMyRenderer.allocDrawParams();
		params.setScreenClear(1.0f, 0.0f, 0.0f);

		// ステージ描画
		mStage.draw(mUFO.mPos.X - MainRenderer.CONTENTS_W / 4);

		// UFOの描画
		mUFO.draw();
	}
}
