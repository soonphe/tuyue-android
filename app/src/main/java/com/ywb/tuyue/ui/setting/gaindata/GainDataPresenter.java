package com.ywb.tuyue.ui.setting.gaindata;

import android.net.Uri;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.micro.player.service.DrmService;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.dto.TStatsDto;
import com.ywb.tuyue.dto.TUserDto;
import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TArticle;
import com.ywb.tuyue.entity.TBook;
import com.ywb.tuyue.entity.TCity;
import com.ywb.tuyue.entity.TCityArticle;
import com.ywb.tuyue.entity.TDataVersion;
import com.ywb.tuyue.entity.TFood;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.entity.TMovie;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.entity.TUser;
import com.ywb.tuyue.entity.TVersion;
import com.ywb.tuyue.entity.TVideo;
import com.ywb.tuyue.ui.mvp.BasePresenter;
import com.ywb.tuyue.utils.HTMLFormatUtils;
import com.ywb.tuyue.utils.LocalPathUtils;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.ywb.tuyue.constants.Constants.CURRENT_DOWNLOAD_COUNT;
import static com.ywb.tuyue.constants.Constants.DOWNLOAD_COUNT;
import static com.ywb.tuyue.constants.Constants.DOWNLOAD_PATH;

@PerActivity
public class GainDataPresenter extends BasePresenter<GainDataContract.View> implements GainDataContract.Presenter {

    private AppApi api;

    @Inject
    public GainDataPresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getDataVersion() {
        mDisposable.add(api.getDataVersion().subscribe(list -> {
                    if (LitePal.findFirst(TDataVersion.class) != null) {
                        LitePal.deleteAll(TDataVersion.class);
                    }
                    list.save();
                    mView.getDataVersionSuccess(list);

                },
                throwable -> mView.onError(throwable.getMessage()))
        );
    }

    @Override
    public void getAdvertList() {
        mDisposable.add(api.getAdvertList(1000).subscribe(list -> {
                    if (LitePal.findFirst(TAdvert.class) != null) {
                        LitePal.deleteAll(TAdvert.class);
                    }
                    LitePal.saveAll(list);
                    for (TAdvert advert : list) {
                        downloadFile(advert, 0, Constants.BASE_IMAGE_URL + advert.getPicurl());
                        downloadFile(advert, 1, HTMLFormatUtils.getImgStr(advert.getContent()));

                    }
                    mView.getAdvertSuccess(list);
                },
                throwable -> mView.onError(throwable.getMessage()))
        );
    }

    @Override
    public void getOtherData() {
        mDisposable.add(
                Observable.mergeArray(
                        api.getAdvertType(),
                        api.getVideoType(),
                        api.getVideoList(1000),
                        api.getGameType(),
                        api.getGameList(1000),
                        api.getBookType(),
                        api.getBookList(1000),
                        api.getFoodType(),
                        api.getFoodList(1000),
                        api.getCityList(1000),
                        api.getCityArticleList(-1, 1000),
                        api.getArticleType(),
                        api.getArticleList(1000))
                        .subscribe((List<? extends LitePalSupport> list) -> {
                                    //删除对应的数据库表文件
                                    if (list.size() > 0) {
                                        LitePal.deleteAll(list.get(0).getClass());
                                    }
                                    for (LitePalSupport object : list) {
                                        //存储所有结果集
                                        object.save();
                                        /**
                                         * 下载所有文件
                                         * type:0图片,1文件
                                         */
                                        if (object instanceof TVideo) {
                                            downloadFile(object, 0, Constants.BASE_IMAGE_URL + ((TVideo) object).getPosterurl());
                                            downloadFile(object, 1, Constants.BASE_IMAGE_URL + ((TVideo) object).getFilepath());
                                        } else if (object instanceof TGame) {
                                            downloadFile(object, 0, Constants.BASE_IMAGE_URL + ((TGame) object).getPicurl());
                                            downloadFile(object, 1, Constants.BASE_IMAGE_URL + ((TGame) object).getFilepath());
                                        } else if (object instanceof TBook) {
                                            if (!StringUtils.isEmpty(((TBook) object).getPicurl())) {
                                                downloadFile(object, 0, Constants.BASE_IMAGE_URL + ((TBook) object).getPicurl());
                                            }
                                            if (!StringUtils.isEmpty(((TBook) object).getFilepath())) {
                                                downloadFile(object, 1, Constants.BASE_IMAGE_URL + ((TBook) object).getFilepath());
                                            }
                                        } else if (object instanceof TFood) {
                                            downloadFile(object, 0, Constants.BASE_IMAGE_URL + ((TFood) object).getPicurl());
                                        } else if (object instanceof TCity) {
                                            downloadFile(object, 0, Constants.BASE_IMAGE_URL + ((TCity) object).getPicurl());
                                            downloadFile(object, 1, HTMLFormatUtils.getImgStr(((TCity) object).getContent()));
                                        } else if (object instanceof TCityArticle) {
                                            downloadFile(object, 0, Constants.BASE_IMAGE_URL + ((TCityArticle) object).getPicurl());
                                            downloadFile(object, 1, HTMLFormatUtils.getImgStr(((TCityArticle) object).getContent()).split(",")[0]);
                                        } else if (object instanceof TArticle) {
                                            downloadFile(object, 0, Constants.BASE_IMAGE_URL + ((TArticle) object).getPicurl());
                                            if (((TArticle) object).getClassify() == 0) {
                                                downloadFile(object, 1, HTMLFormatUtils.getImgStr(((TArticle) object).getContent()));
                                            } else {
                                                downloadFile(object, 2, Constants.BASE_IMAGE_URL + ((TArticle) object).getPathfile());
                                            }
                                        }
                                    }
                                },
                                throwable -> mView.onError(throwable.getMessage()))
        );
    }

    @Override
    public void getMovieData() {
        mDisposable.add(api.getMovieList().subscribe(result -> {
                    //存储所有结果集
                    if (LitePal.findFirst(TMovie.class) != null) {
                        LitePal.deleteAll(TMovie.class);
                    }
                    List<TMovie> list = result.getData().getData().getVideos();
                    LogUtils.e("当前list大小：" + list.size());
                    LitePal.saveAll(list);

                    SPUtils.getInstance().put(DOWNLOAD_COUNT, list.size());
                    //获取drm服务对象
                    DrmService drmService = DrmService.getService();
                    boolean isStartService = drmService.startService();
                    if (!drmService.initDrmdecoder()) {  // 初始化
                        ToastUtils.showShort("解密模块启动失败，请重启电视盒子或联系管理员");
                    }
                    for (TMovie tMovie : list) {
                        //根据位置获取当前点击的视频播放url
                        Uri uri = null;
                        if (isStartService) {
                            //获取的url为已解密的直接可播放地址
                            uri = DrmService.getService().getUrl(
                                    tMovie.getSave_name(),
                                    DeviceUtils.getMacAddress(), //传入一个房间或者是盒子的唯一标识
                                    "192.168.1.6",//服务器端的ip
                                    "" //订单标识
                            );
                        }
                        downloadFile(tMovie, 0, tMovie.getFace_pic() + "");
                        downloadFile(tMovie, 1, uri.toString() + "");
                    }
                    mView.getMovieDataSuccess();
                },
                throwable -> mView.onError(throwable.getMessage()))
        );
    }

    @Override
    public void uploadUserData() {
        mView.startLoading();
        //获取所有未上传过的用户数据
        List<TUser> list = LitePal.where("delflag = ?", "0").find(TUser.class);
        if (list.size() > 0) {
            for (TUser tUser : list) {
                TUserDto tUserDto = new TUserDto(tUser);
                mDisposable.add(api.uploadUser("973570", tUserDto).subscribe(obj -> {
                            //上传成功后更新数据状态码
                            tUser.setDelflag(true);
                            tUser.update(tUser.getId());
                            if (tUser == list.get(list.size() - 1)) {
                                //更新到最新一条时保存数据
                                mView.uploadUserDataSuccess();
                            }
                        },
                        throwable -> mView.onError(throwable.getMessage()))
                );
            }
        }
    }

    @Override
    public void uploadStatsData() {
        //获取所有统计数据
        List<TStats> list = LitePal.findAll(TStats.class);
        if (list.size() > 0) {
            List<TStatsDto> dtoList = new ArrayList<>();
            for (TStats tStats : list) {
                TStatsDto tStatsDto = new TStatsDto(tStats);
                dtoList.add(tStatsDto);
            }
            mDisposable.add(api.uploadStatsList(dtoList).subscribe(obj -> {
                        //①上传成功后删除除最新数据之外的所有数据
                        //②不删除，保留所有数据
                        mView.endLoading();
                        mView.uploadStatsDataSuccess();

                    },
                    throwable -> mView.onError(throwable.getMessage()))
            );

        }
    }


    public void downloadFile(LitePalSupport object, int type, String downpath) {
        //截取最后的文件名和尾缀
        String localFilePath = DOWNLOAD_PATH + downpath.substring(downpath.lastIndexOf("/"), downpath.length());
        //判断本地文件是否存在
        if (FileUtils.isFileExists(localFilePath)) {
            LogUtils.e("本地文件存在" + localFilePath);
            if (object instanceof TAdvert) {
                LogUtils.e("设置广告文件");
                if (type == 0) {
                    ((TAdvert) object).setDownloadPic(localFilePath);
                } else {
                    ((TAdvert) object).setDownloadContent(localFilePath);
                }
                (object).update(((TAdvert) object).getId());
            } else if (object instanceof TVideo) {
                LogUtils.e("设置视频文件");
                if (type == 0) {
                    ((TVideo) object).setDownloadPic(localFilePath);
                } else {
                    ((TVideo) object).setDownloadFile(localFilePath);
                }
                (object).update(((TVideo) object).getId());
            } else if (object instanceof TGame) {
                if (type == 0) {
                    LogUtils.e("设置游戏图片");
                    ((TGame) object).setDownloadPic(localFilePath);
                } else {
                    LogUtils.e("设置游戏文件路径");
                    ((TGame) object).setDownloadFile(localFilePath);
                }
                (object).update(((TGame) object).getId());
            } else if (object instanceof TBook) {
                if (type == 0) {
                    LogUtils.e("设置书吧图片");
                    ((TBook) object).setDownloadPic(localFilePath);
                } else {
                    LogUtils.e("设置书吧文件");
                    ((TBook) object).setDownloadFile(localFilePath);
                }
                (object).update(((TBook) object).getId());
            } else if (object instanceof TFood) {
                LogUtils.e("设置点餐文件");
                ((TFood) object).setDownloadPic(localFilePath);
                (object).update(((TFood) object).getId());
            } else if (object instanceof TCity) {
                LogUtils.e("设置城市文件");
                if (type == 0) {
                    ((TCity) object).setDownloadPic(localFilePath);
                } else {
                    ((TCity) object).setDownloadContent(localFilePath);
                }
                (object).update(((TCity) object).getId());
            } else if (object instanceof TCityArticle) {
                LogUtils.e("设置城市文件");
                if (type == 0) {
                    ((TCityArticle) object).setDownloadPic(localFilePath);
                } else {
                    ((TCityArticle) object).setDownloadContent(localFilePath);
                }
                (object).update(((TCityArticle) object).getId());
            } else if (object instanceof TArticle) {
                LogUtils.e("设置城铁文件");
                if (type == 0) {
                    ((TArticle) object).setDownloadPic(localFilePath);
                } else {
                    ((TArticle) object).setDownloadFile(localFilePath);
                }
                (object).update(((TArticle) object).getId());
            } else if (object instanceof TMovie) {
                if (type == 0) {
                    LogUtils.e("直接设置1905电影封面");
                    ((TMovie) object).setDownloadPic(localFilePath);
                } else {
                    LogUtils.e("直接设置1905电影文件");
                    ((TMovie) object).setDownloadFile(localFilePath);

                    int count = SPUtils.getInstance().getInt(DOWNLOAD_COUNT, 0);
                    int currencount = SPUtils.getInstance().getInt(CURRENT_DOWNLOAD_COUNT, 0);
                    if (currencount < count) {
                        SPUtils.getInstance().put(CURRENT_DOWNLOAD_COUNT, currencount + 1);
                    } else {
                        mView.endLoading();
                    }
                }

                (object).update(((TMovie) object).getId());

            }

        } else {
            String destFileName = null;
            /**
             * 1905需要设置电影返回名称，否则保存为ts文件
             */
            if ((object instanceof TMovie)) {
                destFileName = LocalPathUtils.getFileName(downpath);
            }
            OkGo.<File>get(downpath)
                    .tag(this)
                    .execute(new FileCallback(destFileName) {
                        @Override
                        public void onSuccess(Response<File> response) {
                            LogUtils.e("获取到的文件路径为：" + response.body().getPath());
                            if (object instanceof TAdvert) {
                                LogUtils.e("设置广告文件");
                                if (type == 0) {
                                    ((TAdvert) object).setDownloadPic(response.body().getPath());
                                } else {
                                    ((TAdvert) object).setDownloadContent(response.body().getPath());
                                }
                                (object).update(((TAdvert) object).getId());
                            } else if (object instanceof TVideo) {
                                LogUtils.e("设置视频文件");
                                if (type == 0) {
                                    ((TVideo) object).setDownloadPic(response.body().getPath());
                                } else {
                                    ((TVideo) object).setDownloadFile(response.body().getPath());
                                }
                                (object).update(((TVideo) object).getId());
                            } else if (object instanceof TGame) {
                                if (type == 0) {
                                    LogUtils.e("设置游戏图片");
                                    ((TGame) object).setDownloadPic(response.body().getPath());
                                } else {
                                    LogUtils.e("设置游戏文件路径");
                                    ((TGame) object).setDownloadFile(response.body().getPath());
                                }
                                (object).update(((TGame) object).getId());
                            } else if (object instanceof TBook) {
                                if (type == 0) {
                                    LogUtils.e("设置书吧图片");
                                    ((TBook) object).setDownloadPic(response.body().getPath());
                                } else {
                                    LogUtils.e("设置书吧文件");
                                    ((TBook) object).setDownloadFile(response.body().getPath());
                                }
                                (object).update(((TBook) object).getId());
                            } else if (object instanceof TFood) {
                                LogUtils.e("设置点餐文件");
                                ((TFood) object).setDownloadPic(response.body().getPath());
                                (object).update(((TFood) object).getId());
                            } else if (object instanceof TCity) {
                                LogUtils.e("设置城市文件");
                                if (type == 0) {
                                    ((TCity) object).setDownloadPic(response.body().getPath());
                                } else {
                                    ((TCity) object).setDownloadContent(response.body().getPath());
                                }
                                (object).update(((TCity) object).getId());
                            } else if (object instanceof TCityArticle) {
                                LogUtils.e("设置城市文件");
                                if (type == 0) {
                                    ((TCityArticle) object).setDownloadPic(response.body().getPath());
                                } else {
                                    ((TCityArticle) object).setDownloadContent(response.body().getPath());
                                }
                                (object).update(((TCityArticle) object).getId());
                            } else if (object instanceof TArticle) {
                                LogUtils.e("设置城铁文件");
                                if (type == 0) {
                                    ((TArticle) object).setDownloadPic(response.body().getPath());
                                } else {
                                    ((TArticle) object).setDownloadFile(response.body().getPath());
                                }
                                (object).update(((TArticle) object).getId());
                            } else if (object instanceof TMovie) {
                                if (type == 0) {
                                    LogUtils.e("设置1905电影封图片");
                                    ((TMovie) object).setDownloadPic(response.body().getPath());
                                } else {
                                    LogUtils.e("设置1905电影文件" +
                                            response.body().toString() +
                                            "____" +
                                            response.body().getAbsolutePath() +
                                            "____" +
                                            response.body().getPath());
                                    ((TMovie) object).setDownloadFile(response.body().toString());

                                    int count = SPUtils.getInstance().getInt(DOWNLOAD_COUNT, 0);
                                    int currencount = SPUtils.getInstance().getInt(CURRENT_DOWNLOAD_COUNT, 0);
                                    if (currencount < count) {
                                        SPUtils.getInstance().put(CURRENT_DOWNLOAD_COUNT, currencount + 1);
                                    } else {
                                        mView.endLoading();
                                    }
                                }
                                (object).update(((TMovie) object).getId());

                            }
                        }

                        @Override
                        public void downloadProgress(Progress progress) {
                            LogUtils.e(progress.fileName +
                                    progress.currentSize +
                                    "下载进度为：" + progress.fraction * 100 + "%");
                            //回调下载进度
                            super.downloadProgress(progress);

                        }

                        @Override
                        public void onError(Response<File> response) {
                            LogUtils.e("下载报错,相关类:" + object.getClass() + object.toString());
                            super.onError(response);
                        }
                    });

        }


        //使用okserver download下载文件
//        GetRequest<File> request = OkGo.<File>get(Constants.BASE_IMAGE_URL + downpath);
//        DownloadTask task = OkDownload.request("task",request)
////                .priority()   //优先级
////                .folder() //存储文件夹
////                .fileName() //存储文件名
//                .extra1()
//                .extra2()
//                .extra3()
//                .save()
//                .register();
//        //启动下载任务
//        task.start();
    }


}
