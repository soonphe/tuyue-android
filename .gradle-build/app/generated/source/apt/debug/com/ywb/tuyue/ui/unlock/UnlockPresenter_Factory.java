package com.ywb.tuyue.ui.unlock;

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
public final class UnlockPresenter_Factory implements Factory<UnlockPresenter> {
  private final MembersInjector<UnlockPresenter> unlockPresenterMembersInjector;

  private final Provider<Context> contextProvider;

  private final Provider<AppApi> accountApiProvider;

  public UnlockPresenter_Factory(
      MembersInjector<UnlockPresenter> unlockPresenterMembersInjector,
      Provider<Context> contextProvider,
      Provider<AppApi> accountApiProvider) {
    assert unlockPresenterMembersInjector != null;
    this.unlockPresenterMembersInjector = unlockPresenterMembersInjector;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
    assert accountApiProvider != null;
    this.accountApiProvider = accountApiProvider;
  }

  @Override
  public UnlockPresenter get() {
    return MembersInjectors.injectMembers(
        unlockPresenterMembersInjector,
        new UnlockPresenter(contextProvider.get(), accountApiProvider.get()));
  }

  public static Factory<UnlockPresenter> create(
      MembersInjector<UnlockPresenter> unlockPresenterMembersInjector,
      Provider<Context> contextProvider,
      Provider<AppApi> accountApiProvider) {
    return new UnlockPresenter_Factory(
        unlockPresenterMembersInjector, contextProvider, accountApiProvider);
  }
}
