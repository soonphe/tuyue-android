package com.ywb.tuyue.ui.main;

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
public final class MainPresenter_Factory implements Factory<MainPresenter> {
  private final MembersInjector<MainPresenter> mainPresenterMembersInjector;

  private final Provider<Context> contextProvider;

  private final Provider<AppApi> apiProvider;

  public MainPresenter_Factory(
      MembersInjector<MainPresenter> mainPresenterMembersInjector,
      Provider<Context> contextProvider,
      Provider<AppApi> apiProvider) {
    assert mainPresenterMembersInjector != null;
    this.mainPresenterMembersInjector = mainPresenterMembersInjector;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
    assert apiProvider != null;
    this.apiProvider = apiProvider;
  }

  @Override
  public MainPresenter get() {
    return MembersInjectors.injectMembers(
        mainPresenterMembersInjector, new MainPresenter(contextProvider.get(), apiProvider.get()));
  }

  public static Factory<MainPresenter> create(
      MembersInjector<MainPresenter> mainPresenterMembersInjector,
      Provider<Context> contextProvider,
      Provider<AppApi> apiProvider) {
    return new MainPresenter_Factory(mainPresenterMembersInjector, contextProvider, apiProvider);
  }
}
