package com.ywb.tuyue.di.component;

import android.content.Context;
import android.view.LayoutInflater;
import com.ywb.tuyue.MyApplication;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.module.ApiModule;
import com.ywb.tuyue.di.module.ApiModule_ProvideAppApiFactory;
import com.ywb.tuyue.di.module.ApplicationModule;
import com.ywb.tuyue.di.module.ApplicationModule_ProvideApplicationContextFactory;
import com.ywb.tuyue.di.module.ApplicationModule_ProvideLayoutInflaterFactory;
import com.ywb.tuyue.di.module.ApplicationModule_ProvideOkHttpClientFactory;
import com.ywb.tuyue.di.module.DBModule;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerApplicationComponent implements ApplicationComponent {
  private Provider<Context> provideApplicationContextProvider;

  private Provider<LayoutInflater> provideLayoutInflaterProvider;

  private Provider<OkHttpClient> provideOkHttpClientProvider;

  private Provider<AppApi> provideAppApiProvider;

  private DaggerApplicationComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideApplicationContextProvider =
        DoubleCheck.provider(
            ApplicationModule_ProvideApplicationContextFactory.create(builder.applicationModule));

    this.provideLayoutInflaterProvider =
        DoubleCheck.provider(
            ApplicationModule_ProvideLayoutInflaterFactory.create(
                builder.applicationModule, provideApplicationContextProvider));

    this.provideOkHttpClientProvider =
        DoubleCheck.provider(
            ApplicationModule_ProvideOkHttpClientFactory.create(builder.applicationModule));

    this.provideAppApiProvider =
        DoubleCheck.provider(
            ApiModule_ProvideAppApiFactory.create(builder.apiModule, provideOkHttpClientProvider));
  }

  @Override
  public Context getContext() {
    return provideApplicationContextProvider.get();
  }

  @Override
  public LayoutInflater getLayoutInflater() {
    return provideLayoutInflaterProvider.get();
  }

  @Override
  public AppApi getAccountApi() {
    return provideAppApiProvider.get();
  }

  @Override
  public void inject(MyApplication mApplication) {
    MembersInjectors.<MyApplication>noOp().injectMembers(mApplication);
  }

  @Override
  public void inject(BaseActivity mBaseActivity) {
    MembersInjectors.<BaseActivity>noOp().injectMembers(mBaseActivity);
  }

  public static final class Builder {
    private ApplicationModule applicationModule;

    private ApiModule apiModule;

    private Builder() {}

    public ApplicationComponent build() {
      if (applicationModule == null) {
        throw new IllegalStateException(
            ApplicationModule.class.getCanonicalName() + " must be set");
      }
      if (apiModule == null) {
        this.apiModule = new ApiModule();
      }
      return new DaggerApplicationComponent(this);
    }

    public Builder applicationModule(ApplicationModule applicationModule) {
      this.applicationModule = Preconditions.checkNotNull(applicationModule);
      return this;
    }

    public Builder apiModule(ApiModule apiModule) {
      this.apiModule = Preconditions.checkNotNull(apiModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder dBModule(DBModule dBModule) {
      Preconditions.checkNotNull(dBModule);
      return this;
    }
  }
}
