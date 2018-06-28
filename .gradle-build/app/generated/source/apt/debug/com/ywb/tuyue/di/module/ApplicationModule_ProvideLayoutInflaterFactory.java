package com.ywb.tuyue.di.module;

import android.content.Context;
import android.view.LayoutInflater;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApplicationModule_ProvideLayoutInflaterFactory
    implements Factory<LayoutInflater> {
  private final ApplicationModule module;

  private final Provider<Context> contextProvider;

  public ApplicationModule_ProvideLayoutInflaterFactory(
      ApplicationModule module, Provider<Context> contextProvider) {
    assert module != null;
    this.module = module;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
  }

  @Override
  public LayoutInflater get() {
    return Preconditions.checkNotNull(
        module.provideLayoutInflater(contextProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<LayoutInflater> create(
      ApplicationModule module, Provider<Context> contextProvider) {
    return new ApplicationModule_ProvideLayoutInflaterFactory(module, contextProvider);
  }

  /** Proxies {@link ApplicationModule#provideLayoutInflater(Context)}. */
  public static LayoutInflater proxyProvideLayoutInflater(
      ApplicationModule instance, Context context) {
    return instance.provideLayoutInflater(context);
  }
}
