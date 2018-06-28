package com.ywb.tuyue.ui.advertise;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AdvertiseDetailActivity_MembersInjector
    implements MembersInjector<AdvertiseDetailActivity> {
  private final Provider<AdvertiseDetailPresenter> mAdvertiseDetailPresenterProvider;

  public AdvertiseDetailActivity_MembersInjector(
      Provider<AdvertiseDetailPresenter> mAdvertiseDetailPresenterProvider) {
    assert mAdvertiseDetailPresenterProvider != null;
    this.mAdvertiseDetailPresenterProvider = mAdvertiseDetailPresenterProvider;
  }

  public static MembersInjector<AdvertiseDetailActivity> create(
      Provider<AdvertiseDetailPresenter> mAdvertiseDetailPresenterProvider) {
    return new AdvertiseDetailActivity_MembersInjector(mAdvertiseDetailPresenterProvider);
  }

  @Override
  public void injectMembers(AdvertiseDetailActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mAdvertiseDetailPresenter = mAdvertiseDetailPresenterProvider.get();
  }

  public static void injectMAdvertiseDetailPresenter(
      AdvertiseDetailActivity instance,
      Provider<AdvertiseDetailPresenter> mAdvertiseDetailPresenterProvider) {
    instance.mAdvertiseDetailPresenter = mAdvertiseDetailPresenterProvider.get();
  }
}
