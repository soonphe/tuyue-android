package com.ywb.tuyue.ui.book.bookread;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bifan.txtreaderlib.bean.TxtMsg;
import com.bifan.txtreaderlib.interfaces.ILoadListener;
import com.bifan.txtreaderlib.main.TxtReaderView;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.LogUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TBook;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.widget.AppTitle;

import org.litepal.LitePal;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @Author soonphe
 * @Date 2018-08-28 11:31
 * @Description 书籍阅读
 */
public class BookreadActivity extends BaseActivity implements BookreadContract.View {

    @BindView(R.id.app_title_id)
    AppTitle appTitle;
    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.activity_hwtxtplay_readerView)
    TxtReaderView activityHwtxtplayReaderView;
    @BindView(R.id.show_qq)
    TextView showQq;
    @BindView(R.id.last_page)
    Button lastPage;
    @BindView(R.id.next_page)
    Button nextPage;
    @BindView(R.id.rootView)
    LinearLayout rootView;

    //book的ID
    int id;
    TBook tBook;

    private int mLastTimeReadSite; //记录上次阅读的位置
    private int mDefaultReadPage; //记录默认阅读的位置
    private int mCurrentReagPage = 1; //记录当前阅读的页数

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_book_read;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

        BarUtils.setStatusBarAlpha(this, 0);

        //取传过来的分类ID
        if (EmptyUtils.isNotEmpty(mOperation.getParameter("book"))) {
            id = (int) mOperation.getParameter("book");
        }
        tBook = LitePal.find(TBook.class, id );
        appTitle.setTitle(tBook.getName());

        if (tBook.getDownloadFile().endsWith("txt")) {
//            mLastTimeReadSite = SPUtils.getInstance().getInt(mBookName);//Sp存储上次阅读的位置
            nextPage.setVisibility(View.VISIBLE);
            lastPage.setVisibility(View.VISIBLE);
//            showQq.setText(R.string.ad_qq);

            if (mLastTimeReadSite != 0) {
                mCurrentReagPage = mLastTimeReadSite; //如果上次阅读的位置不是0，就赋值给当前页
            }
            try {
                //使用默认界面
//                HwTxtPlayActivity.loadTxtFile(this, tBook.getDownloadFile(), tBook.getName());
                //使用自定义界面
                activityHwtxtplayReaderView.loadTxtFile(tBook.getDownloadFile(),new ILoadListener() {
                    @Override
                    public void onSuccess() {
                        //加载成功回调
                        LogUtils.e("加载成功");
                    }

                    @Override
                    public void onFail(TxtMsg txtMsg) {
                        //加载失败回调
                    }

                    @Override
                    public void onMessage(String message) {
                        //加载过程信息回调
                    }
                } );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }



    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @OnClick({R.id.last_page, R.id.next_page})
    public void onViewClicked(View view) {
        int mode=0;
        switch (view.getId()) {
            case R.id.last_page:
                mode = ScrollView.FOCUS_DOWN;//滚动到底部
                mCurrentReagPage--;
                activityHwtxtplayReaderView.jumpToPreChapter();
                break;
            case R.id.next_page:
                mode = ScrollView.FOCUS_UP;//滚动到顶部
                mCurrentReagPage++;
                activityHwtxtplayReaderView.jumpToNextChapter();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appTitle.getStatusLine().unregisterBroadcast();
    }
}
