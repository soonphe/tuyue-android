package com.ywb.tuyue.ui.main;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<MainPresenter> mMainPresenterProvider;

  public MainActivity_MembersInjector(Provider<MainPresenter> mMainPresenterProvider) {
    assert mMainPresenterProvider != null;
    this.mMainPresenterProvider = mMainPresenterProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<MainPresenter> mMainPresenterProvider) {
    return new MainActivity_MembersInjector(mMainPresenterProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mMainPresenter = mMainPresenterProvider.get();
  }

  public static void injectMMainPresenter(
      MainActivity instance, Provider<MainPresenter> mMainPresenterProvider) {
    instance.mMainPresenter = mMainPresenterProvider.get();
  }
}
