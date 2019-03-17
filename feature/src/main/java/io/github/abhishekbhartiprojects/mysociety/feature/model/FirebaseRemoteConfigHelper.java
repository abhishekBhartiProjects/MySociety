package io.github.abhishekbhartiprojects.mysociety.feature.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import io.github.abhishekbhartiprojects.mysociety.feature.BuildConfig;
import io.github.abhishekbhartiprojects.mysociety.feature.R;
import io.github.abhishekbhartiprojects.mysociety.feature.android.MyApplication;
import io.reactivex.annotations.NonNull;

public class FirebaseRemoteConfigHelper {

    private static FirebaseRemoteConfigHelper mInstance;
    public FirebaseRemoteConfig mFirebaseRemoteConfig;

    public static FirebaseRemoteConfigHelper getInstance() {
        if (mInstance == null) mInstance = new FirebaseRemoteConfigHelper();
        mInstance.forceFetch();
        return mInstance;
    }

    private FirebaseRemoteConfigHelper() {
        mFirebaseRemoteConfig = MyApplication.Companion.getFirebaseRemoteConfig();
        if (mFirebaseRemoteConfig == null) return;
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        forceFetch();
    }

    public void forceFetch() {
        if (mFirebaseRemoteConfig == null) return;

        long cacheExpiration = 3600; // 1 hour in seconds.
        // If in developer mode cacheExpiration is set to 0 so each fetch will retrieve values from
        // the server.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Once the config is successfully fetched it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                        }
                    }
                });

    }

    public static final String SHEET_ENDPOINT = "sheet_endpoint";
    public String getSheetEndpoint(){
        return MyApplication.mFirebaseRemoteConfig.getString(SHEET_ENDPOINT);
    }


    public static final String HYDE_PARK_SHEET_ID = "hyde_park_sheetid";
    public String getHydeParkSheetId(){
        return MyApplication.mFirebaseRemoteConfig.getString(HYDE_PARK_SHEET_ID);
    }

    public static final String MEMBER_INFO_PAGE = "member_info_page";
    public String getMemberInfoPage(){
        return MyApplication.mFirebaseRemoteConfig.getString(MEMBER_INFO_PAGE);
    }

    public static final String ADD_MEMBER_REQUEST_PAGE = "add_member_request_page";
    public String getAddMemberRequestPage(){
        return MyApplication.mFirebaseRemoteConfig.getString(ADD_MEMBER_REQUEST_PAGE);
    }

    public static final String RENT_PAGE = "rent_page";
    public String getRentPage(){
        return MyApplication.mFirebaseRemoteConfig.getString(RENT_PAGE);
    }
}
