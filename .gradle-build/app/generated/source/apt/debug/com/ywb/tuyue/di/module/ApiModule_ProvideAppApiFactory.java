package com.ywb.tuyue.di.module;

import com.ywb.tuyue.api.AppApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApiModule_ProvideAppApiFactory implements Factory<AppApi> {
  private final ApiModule module;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public ApiModule_ProvideAppApiFactory(
      ApiModule module, Provider<OkHttpClient> okHttpClientProvider) {
    assert module != null;
    this.module = module;
    assert okHttpClientProvider != null;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public AppApi get() {
    return Preconditions.checkNotNull(
        module.provideAppApi(okHttpClientProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<AppApi> create(
      ApiModule module, Provider<OkHttpClient> okHttpClientProvider) {
    return new ApiModule_ProvideAppApiFactory(module, okHttpClientProvider);
  }
}
