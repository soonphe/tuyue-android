package com.ywb.tuyue.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApplicationModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideOkHttpClientFactory(ApplicationModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.provideOkHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient> create(ApplicationModule module) {
    return new ApplicationModule_ProvideOkHttpClientFactory(module);
  }

  /** Proxies {@link ApplicationModule#provideOkHttpClient()}. */
  public static OkHttpClient proxyProvideOkHttpClient(ApplicationModule instance) {
    return instance.provideOkHttpClient();
  }
}
