package com.ywb.tuyue.di.module;

import android.app.NotificationManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApplicationModule_ProvideNotificationManagerFactory
    implements Factory<NotificationManager> {
  private final ApplicationModule module;

  private final Provider<Context> mContextProvider;

  public ApplicationModule_ProvideNotificationManagerFactory(
      ApplicationModule module, Provider<Context> mContextProvider) {
    assert module != null;
    this.module = module;
    assert mContextProvider != null;
    this.mContextProvider = mContextProvider;
  }

  @Override
  public NotificationManager get() {
    return Preconditions.checkNotNull(
        module.provideNotificationManager(mContextProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<NotificationManager> create(
      ApplicationModule module, Provider<Context> mContextProvider) {
    return new ApplicationModule_ProvideNotificationManagerFactory(module, mContextProvider);
  }

  /** Proxies {@link ApplicationModule#provideNotificationManager(Context)}. */
  public static NotificationManager proxyProvideNotificationManager(
      ApplicationModule instance, Context mContext) {
    return instance.provideNotificationManager(mContext);
  }
}
