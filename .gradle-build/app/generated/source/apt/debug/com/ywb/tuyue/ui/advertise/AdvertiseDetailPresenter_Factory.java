package com.ywb.tuyue.ui.advertise;

import android.content.Context;
import com.ywb.tuyue.api.AppApi;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AdvertiseDetailPresenter_Factory implements Factory<AdvertiseDetailPresenter> {
  private final MembersInjector<AdvertiseDetailPresenter> advertiseDetailPresenterMembersInjector;

  private final Provider<Context> contextProvider;

  private final Provider<AppApi> apiProvider;

  public AdvertiseDetailPresenter_Factory(
      MembersInjector<AdvertiseDetailPresenter> advertiseDetailPresenterMembersInjector,
      Provider<Context> contextProvider,
      Provider<AppApi> apiProvider) {
    assert advertiseDetailPresenterMembersInjector != null;
    this.advertiseDetailPresenterMembersInjector = advertiseDetailPresenterMembersInjector;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
    assert apiProvider != null;
    this.apiProvider = apiProvider;
  }

  @Override
  public AdvertiseDetailPresenter get() {
    return MembersInjectors.injectMembers(
        advertiseDetailPresenterMembersInjector,
        new AdvertiseDetailPresenter(contextProvider.get(), apiProvider.get()));
  }

  public static Factory<AdvertiseDetailPresenter> create(
      MembersInjector<AdvertiseDetailPresenter> advertiseDetailPresenterMembersInjector,
      Provider<Context> contextProvider,
      Provider<AppApi> apiProvider) {
    return new AdvertiseDetailPresenter_Factory(
        advertiseDetailPresenterMembersInjector, contextProvider, apiProvider);
  }
}
