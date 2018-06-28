package com.ywb.tuyue.ui.unlock;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UnlockActivity_MembersInjector implements MembersInjector<UnlockActivity> {
  private final Provider<UnlockPresenter> mUnlockPresenterProvider;

  public UnlockActivity_MembersInjector(Provider<UnlockPresenter> mUnlockPresenterProvider) {
    assert mUnlockPresenterProvider != null;
    this.mUnlockPresenterProvider = mUnlockPresenterProvider;
  }

  public static MembersInjector<UnlockActivity> create(
      Provider<UnlockPresenter> mUnlockPresenterProvider) {
    return new UnlockActivity_MembersInjector(mUnlockPresenterProvider);
  }

  @Override
  public void injectMembers(UnlockActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mUnlockPresenter = mUnlockPresenterProvider.get();
  }

  public static void injectMUnlockPresenter(
      UnlockActivity instance, Provider<UnlockPresenter> mUnlockPresenterProvider) {
    instance.mUnlockPresenter = mUnlockPresenterProvider.get();
  }
}
