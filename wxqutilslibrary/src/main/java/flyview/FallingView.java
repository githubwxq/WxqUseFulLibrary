package flyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.wxq.wxqutilslibrary.R;

/**
 * Created by dingmouren on 2017/4/28.
 */

public class FallingView extends RelativeLayout {
    private static final String TAG = FallingView.class.getName();
    private static final int DEFAULT_FLAKES_DENSITY = 80;
    private static final int DEFAULT_DELAY = 10;
    private int mFlakesDensity = DEFAULT_FLAKES_DENSITY;
    private int mDelay = DEFAULT_DELAY;
    private static final int DEFAULT_SCALE = 3;
    private Flake[] mFlakes;
    private Bitmap mFlakeBitmap;
    private Paint mPaint;
    private int mImgId;
    private int mScale;
    private int mWidth;
    private int mHeight;
    private int mRawWidth;
    public FallingView(Context context) {
        this(context,null);
    }

    public FallingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FallingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        setBackgroundColor(Color.TRANSPARENT);
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FallingView);
            mImgId = a.getResourceId(R.styleable.FallingView_flakeSrc,R.mipmap.snow_flake);
            mScale = a.getInt(R.styleable.FallingView_flakeScale,DEFAULT_SCALE);
            mFlakesDensity = a.getInt(R.styleable.FallingView_flakeDensity,DEFAULT_FLAKES_DENSITY);
            mDelay = a.getInt(R.styleable.FallingView_fallingDelay,DEFAULT_DELAY);

        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh ){
            mWidth = w;
            mHeight = h;
            mRawWidth = initScale(mScale);
            initDenstity(w,h,mRawWidth);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        for (Flake flake : mFlakes){
            flake.draw(canvas,mFlakeBitmap);
        }
        getHandler().postDelayed(mRunnable,mDelay);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    private void initDenstity(int w,int h,int rawWidth){
        mFlakes = new Flake[mFlakesDensity];
        for (int i = 0; i < mFlakesDensity; i++) {
            mFlakes[i] = Flake.create(w,h,mPaint,rawWidth/mScale);
        }
    }

    private int initScale(int scale){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), mImgId, options);
        int rawWidth = options.outWidth;
        mRawWidth = rawWidth;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        mFlakeBitmap = BitmapFactory.decodeResource(getResources(), mImgId, options);
        return rawWidth;
    }

    public void setImageResource( int imgId){
        this.mImgId = imgId;
        initScale(mScale);
    }

    public void setScale(int scale){
        initScale(scale);
    }

    public void setDensity(int density){
        this.mFlakesDensity = density;
        initDenstity(mWidth,mHeight,mRawWidth);
    }

    public void setDelay(int delay){
        this.mDelay = delay;
    }
}


//public class MainActivity extends AppCompatActivity {
//    private SeekBar seek_size,seek_v,seek_density;
//    private FallingView mFallingView;
//    private ImageView img;
//    private Toolbar toolbar;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        seek_size = (SeekBar) findViewById(R.id.seek_size);
//        seek_v = (SeekBar) findViewById(R.id.seek_v);
//        seek_density = (SeekBar) findViewById(R.id.seek_density);
//        mFallingView = (FallingView) findViewById(R.id.falling_view);
//        img = (ImageView) findViewById(R.id.img);
//        toolbar = (Toolbar) findViewById(R.id.toobar);
//        toolbar.setTitle("FallingView");
//        setSupportActionBar(toolbar);
//        initListener();
//        img.setImageResource(R.drawable.bg1);
//    }
//
//    private void initListener() {
//
//        seek_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mFallingView.setScale(progress);//设置碎片的大小，数值越大，碎片越小，默认值是3
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//
//        seek_density.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mFallingView.setDensity(progress);//设置密度，数值越大，碎片越密集
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//
//        seek_v.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mFallingView.setDelay(progress);//设置碎片飘落的速度，数值越大，飘落的越慢，默认值是10
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.img1:
//                mFallingView.setImageResource(R.drawable.img1);
//                img.setImageResource(R.drawable.bg2);
//                break;
//            case R.id.img2:
//                mFallingView.setImageResource(R.drawable.img2);
//                img.setImageResource(R.drawable.bg3);
//                break;
//            case R.id.img3:
//                mFallingView.setImageResource(R.drawable.img3);
//                img.setImageResource(0);
//                img.setBackgroundColor(Color.parseColor("#ff6f00"));
//                break;
//            case R.id.img4:
//                mFallingView.setImageResource(R.drawable.img4);
//                img.setImageResource(R.drawable.bg4);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
