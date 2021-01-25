package com.vaskevicius.android.vikingrecipe.data.prefs;

import android.content.Context;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppPreferenceHelper_Factory implements Factory<AppPreferenceHelper> {
  private final Provider<Context> contextProvider;

  private final Provider<String> prefFileNameProvider;

  public AppPreferenceHelper_Factory(Provider<Context> contextProvider,
      Provider<String> prefFileNameProvider) {
    this.contextProvider = contextProvider;
    this.prefFileNameProvider = prefFileNameProvider;
  }

  @Override
  public AppPreferenceHelper get() {
    return newInstance(contextProvider.get(), prefFileNameProvider.get());
  }

  public static AppPreferenceHelper_Factory create(Provider<Context> contextProvider,
      Provider<String> prefFileNameProvider) {
    return new AppPreferenceHelper_Factory(contextProvider, prefFileNameProvider);
  }

  public static AppPreferenceHelper newInstance(Context context, String prefFileName) {
    return new AppPreferenceHelper(context, prefFileName);
  }
}
