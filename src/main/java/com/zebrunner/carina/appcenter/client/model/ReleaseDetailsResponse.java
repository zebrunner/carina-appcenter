package com.zebrunner.carina.appcenter.client.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.zebrunner.carina.appcenter.client.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class ReleaseDetailsResponse {
    private static final String UNEXPECTED_VALUE_ERROR_MESSAGE = "Unexpected value '%s'";

    public static final String SERIALIZED_NAME_ID = "id";
    public static final String SERIALIZED_NAME_APP_NAME = "app_name";
    public static final String SERIALIZED_NAME_APP_DISPLAY_NAME = "app_display_name";
    public static final String SERIALIZED_NAME_APP_OS = "app_os";
    public static final String SERIALIZED_NAME_VERSION = "version";
    public static final String SERIALIZED_NAME_ORIGIN = "origin";
    public static final String SERIALIZED_NAME_SHORT_VERSION = "short_version";
    public static final String SERIALIZED_NAME_RELEASE_NOTES = "release_notes";
    public static final String SERIALIZED_NAME_PROVISIONING_PROFILE_NAME = "provisioning_profile_name";
    public static final String SERIALIZED_NAME_PROVISIONING_PROFILE_TYPE = "provisioning_profile_type";
    public static final String SERIALIZED_NAME_PROVISIONING_PROFILE_EXPIRY_DATE = "provisioning_profile_expiry_date";
    public static final String SERIALIZED_NAME_IS_PROVISIONING_PROFILE_SYNCING = "is_provisioning_profile_syncing";
    public static final String SERIALIZED_NAME_SIZE = "size";
    public static final String SERIALIZED_NAME_MIN_OS = "min_os";
    public static final String SERIALIZED_NAME_DEVICE_FAMILY = "device_family";
    public static final String SERIALIZED_NAME_ANDROID_MIN_API_LEVEL = "android_min_api_level";
    public static final String SERIALIZED_NAME_BUNDLE_IDENTIFIER = "bundle_identifier";
    public static final String SERIALIZED_NAME_PACKAGE_HASHES = "package_hashes";
    public static final String SERIALIZED_NAME_FINGERPRINT = "fingerprint";
    public static final String SERIALIZED_NAME_UPLOADED_AT = "uploaded_at";
    public static final String SERIALIZED_NAME_DOWNLOAD_URL = "download_url";
    public static final String SERIALIZED_NAME_SECONDARY_DOWNLOAD_URL = "secondary_download_url";
    public static final String SERIALIZED_NAME_APP_ICON_URL = "app_icon_url";
    public static final String SERIALIZED_NAME_INSTALL_URL = "install_url";
    public static final String SERIALIZED_FILE_EXTENSION = "fileExtension";
    public static final String SERIALIZED_NAME_DESTINATION_TYPE = "destination_type";
    public static final String SERIALIZED_NAME_DISTRIBUTION_GROUPS = "distribution_groups";
    public static final String SERIALIZED_NAME_DISTRIBUTION_STORES = "distribution_stores";
    public static final String SERIALIZED_NAME_DESTINATIONS = "destinations";
    public static final String SERIALIZED_NAME_IS_UDID_PROVISIONED = "is_udid_provisioned";
    public static final String SERIALIZED_NAME_CAN_RESIGN = "can_resign";
    public static final String SERIALIZED_NAME_BUILD = "build";
    public static final String SERIALIZED_NAME_ENABLED = "enabled";
    public static final String SERIALIZED_NAME_STATUS = "status";
    public static final String SERIALIZED_NAME_IS_EXTERNAL_BUILD = "is_external_build";
    private static final Set<String> OPENAPI_FIELDS;
    private static final Set<String> OPENAPI_REQUIRED_FIELDS;

    static {
        // a set of all properties/fields (JSON key names)
        OPENAPI_FIELDS = new HashSet<>();
        OPENAPI_FIELDS.add(SERIALIZED_NAME_ID);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_APP_NAME);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_APP_DISPLAY_NAME);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_APP_OS);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_VERSION);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_ORIGIN);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_SHORT_VERSION);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_RELEASE_NOTES);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_PROVISIONING_PROFILE_NAME);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_PROVISIONING_PROFILE_TYPE);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_PROVISIONING_PROFILE_EXPIRY_DATE);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_IS_PROVISIONING_PROFILE_SYNCING);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_SIZE);
        OPENAPI_FIELDS.add(SERIALIZED_FILE_EXTENSION);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_MIN_OS);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_DEVICE_FAMILY);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_ANDROID_MIN_API_LEVEL);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_BUNDLE_IDENTIFIER);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_PACKAGE_HASHES);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_FINGERPRINT);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_UPLOADED_AT);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_DOWNLOAD_URL);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_SECONDARY_DOWNLOAD_URL);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_APP_ICON_URL);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_INSTALL_URL);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_DESTINATION_TYPE);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_DISTRIBUTION_GROUPS);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_DISTRIBUTION_STORES);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_DESTINATIONS);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_IS_UDID_PROVISIONED);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_CAN_RESIGN);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_BUILD);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_ENABLED);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_STATUS);
        OPENAPI_FIELDS.add(SERIALIZED_NAME_IS_EXTERNAL_BUILD);

        // a set of required properties/fields (JSON key names)
        OPENAPI_REQUIRED_FIELDS = new HashSet<>();
        OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_ID);
        OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_APP_NAME);
        OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_APP_DISPLAY_NAME);
        OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_VERSION);
        OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_SHORT_VERSION);
        OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_UPLOADED_AT);
        OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_ENABLED);
    }

    @SerializedName(SERIALIZED_NAME_ID)
    private Integer id;
    @SerializedName(SERIALIZED_NAME_APP_NAME)
    private String appName;
    @SerializedName(SERIALIZED_NAME_APP_DISPLAY_NAME)
    private String appDisplayName;
    @SerializedName(SERIALIZED_NAME_APP_OS)
    private String appOs;
    @SerializedName(SERIALIZED_NAME_VERSION)
    private String version;
    @SerializedName(SERIALIZED_NAME_ORIGIN)
    private OriginEnum origin;
    @SerializedName(SERIALIZED_NAME_SHORT_VERSION)
    private String shortVersion;
    @SerializedName(SERIALIZED_NAME_RELEASE_NOTES)
    private String releaseNotes;
    @SerializedName(SERIALIZED_NAME_PROVISIONING_PROFILE_NAME)
    private String provisioningProfileName;
    @SerializedName(SERIALIZED_NAME_PROVISIONING_PROFILE_TYPE)
    private ProvisioningProfileTypeEnum provisioningProfileType;
    @SerializedName(SERIALIZED_NAME_PROVISIONING_PROFILE_EXPIRY_DATE)
    private String provisioningProfileExpiryDate;
    @SerializedName(SERIALIZED_NAME_IS_PROVISIONING_PROFILE_SYNCING)
    private Boolean isProvisioningProfileSyncing;
    @SerializedName(SERIALIZED_NAME_SIZE)
    private Integer size;
    @SerializedName(SERIALIZED_NAME_MIN_OS)
    private String minOs;
    @SerializedName(SERIALIZED_NAME_DEVICE_FAMILY)
    private String deviceFamily;
    @SerializedName(SERIALIZED_NAME_ANDROID_MIN_API_LEVEL)
    private String androidMinApiLevel;
    @SerializedName(SERIALIZED_NAME_BUNDLE_IDENTIFIER)
    private String bundleIdentifier;
    @SerializedName(SERIALIZED_NAME_PACKAGE_HASHES)
    private List<String> packageHashes;
    @SerializedName(SERIALIZED_NAME_FINGERPRINT)
    private String fingerprint;
    @SerializedName(SERIALIZED_NAME_UPLOADED_AT)
    private String uploadedAt;
    @SerializedName(SERIALIZED_NAME_DOWNLOAD_URL)
    private String downloadUrl;
    @SerializedName(SERIALIZED_NAME_SECONDARY_DOWNLOAD_URL)
    private String secondaryDownloadUrl;
    @SerializedName(SERIALIZED_NAME_APP_ICON_URL)
    private String appIconUrl;
    @SerializedName(SERIALIZED_NAME_INSTALL_URL)
    private String installUrl;
    @SerializedName(SERIALIZED_FILE_EXTENSION)
    private String fileExtension;
    @SerializedName(SERIALIZED_NAME_DESTINATION_TYPE)
    private DestinationTypeEnum destinationType;
    @SerializedName(SERIALIZED_NAME_DISTRIBUTION_GROUPS)
    private List<Object> distributionGroups;
    @SerializedName(SERIALIZED_NAME_DISTRIBUTION_STORES)
    private List<Object> distributionStores;
    @SerializedName(SERIALIZED_NAME_DESTINATIONS)
    private List<Object> destinations;
    @SerializedName(SERIALIZED_NAME_IS_UDID_PROVISIONED)
    private Boolean isUdidProvisioned;
    @SerializedName(SERIALIZED_NAME_CAN_RESIGN)
    private Boolean canResign;
    @SerializedName(SERIALIZED_NAME_BUILD)
    private Object build;
    @SerializedName(SERIALIZED_NAME_ENABLED)
    private Boolean enabled;
    @SerializedName(SERIALIZED_NAME_STATUS)
    private String status;
    @SerializedName(SERIALIZED_NAME_IS_EXTERNAL_BUILD)
    private Boolean isExternalBuild;

    public ReleaseDetailsResponse() {
        //empty
    }

    /**
     * Validates the JSON Object and throws an exception if issues found
     *
     * @param jsonObj JSON Object
     */
    public static void validateJsonObject(JsonObject jsonObj) {
        if (jsonObj == null && !ReleaseDetailsResponse.OPENAPI_REQUIRED_FIELDS.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("The required field(s) %s in ReleaseDetailsResponse is not found in the empty JSON string",
                            ReleaseDetailsResponse.OPENAPI_REQUIRED_FIELDS));
        }

        Set<Entry<String, JsonElement>> entries = Objects.requireNonNull(jsonObj).entrySet();
        // check to see if the JSON string contains additional fields
        for (Entry<String, JsonElement> entry : entries) {
            if (!ReleaseDetailsResponse.OPENAPI_FIELDS.contains(entry.getKey())) {
                throw new IllegalArgumentException(
                        String.format("The field `%s` in the JSON string is not defined in the `ReleaseDetailsResponse` properties. JSON: %s",
                                entry.getKey(), jsonObj));
            }
        }

        // check to make sure all required properties/fields are present in the JSON string
        for (String requiredField : ReleaseDetailsResponse.OPENAPI_REQUIRED_FIELDS) {
            if (jsonObj.get(requiredField) == null) {
                throw new IllegalArgumentException(
                        String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj));
            }
        }
        if (!jsonObj.get(SERIALIZED_NAME_APP_NAME).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `app_name` to be a primitive type in the JSON string but got `%s`",
                    jsonObj.get(SERIALIZED_NAME_APP_NAME).toString()));
        }
        if (!jsonObj.get(SERIALIZED_NAME_APP_DISPLAY_NAME).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `app_display_name` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_APP_DISPLAY_NAME).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_APP_OS) != null && !jsonObj.get(SERIALIZED_NAME_APP_OS).isJsonNull()) && !jsonObj.get(SERIALIZED_NAME_APP_OS)
                .isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `app_os` to be a primitive type in the JSON string but got `%s`",
                    jsonObj.get(SERIALIZED_NAME_APP_OS).toString()));
        }
        if (!jsonObj.get(SERIALIZED_NAME_VERSION).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `version` to be a primitive type in the JSON string but got `%s`",
                    jsonObj.get(SERIALIZED_NAME_VERSION).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_ORIGIN) != null && !jsonObj.get(SERIALIZED_NAME_ORIGIN).isJsonNull()) && !jsonObj.get(SERIALIZED_NAME_ORIGIN)
                .isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `origin` to be a primitive type in the JSON string but got `%s`",
                    jsonObj.get(SERIALIZED_NAME_ORIGIN).toString()));
        }
        if (!jsonObj.get(SERIALIZED_NAME_SHORT_VERSION).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `short_version` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_SHORT_VERSION).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_RELEASE_NOTES) != null && !jsonObj.get(SERIALIZED_NAME_RELEASE_NOTES).isJsonNull()) && !jsonObj.get(
                SERIALIZED_NAME_RELEASE_NOTES).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `release_notes` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_RELEASE_NOTES).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_NAME) != null && !jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_NAME).isJsonNull())
                && !jsonObj.get(
                SERIALIZED_NAME_PROVISIONING_PROFILE_NAME).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `provisioning_profile_name` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_NAME).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_TYPE) != null && !jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_TYPE).isJsonNull())
                && !jsonObj.get(
                SERIALIZED_NAME_PROVISIONING_PROFILE_TYPE).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `provisioning_profile_type` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_TYPE).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_EXPIRY_DATE) != null && !jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_EXPIRY_DATE)
                .isJsonNull())
                && !jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_EXPIRY_DATE).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `provisioning_profile_expiry_date` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_PROVISIONING_PROFILE_EXPIRY_DATE).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_MIN_OS) != null && !jsonObj.get(SERIALIZED_NAME_MIN_OS).isJsonNull()) && !jsonObj.get(SERIALIZED_NAME_MIN_OS)
                .isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `min_os` to be a primitive type in the JSON string but got `%s`",
                    jsonObj.get(SERIALIZED_NAME_MIN_OS).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_DEVICE_FAMILY) != null && !jsonObj.get(SERIALIZED_NAME_DEVICE_FAMILY).isJsonNull()) && !jsonObj.get(
                SERIALIZED_NAME_DEVICE_FAMILY).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `device_family` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_DEVICE_FAMILY).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_ANDROID_MIN_API_LEVEL) != null && !jsonObj.get(SERIALIZED_NAME_ANDROID_MIN_API_LEVEL).isJsonNull())
                && !jsonObj.get(
                SERIALIZED_NAME_ANDROID_MIN_API_LEVEL).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `android_min_api_level` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_ANDROID_MIN_API_LEVEL).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_BUNDLE_IDENTIFIER) != null && !jsonObj.get(SERIALIZED_NAME_BUNDLE_IDENTIFIER).isJsonNull()) && !jsonObj.get(
                        SERIALIZED_NAME_BUNDLE_IDENTIFIER)
                .isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `bundle_identifier` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_BUNDLE_IDENTIFIER).toString()));
        }
        // ensure the optional json data is an array if present
        if (jsonObj.get(SERIALIZED_NAME_PACKAGE_HASHES) != null && !jsonObj.get(SERIALIZED_NAME_PACKAGE_HASHES).isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `package_hashes` to be an array in the JSON string but got `%s`",
                    jsonObj.get(SERIALIZED_NAME_PACKAGE_HASHES).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_FINGERPRINT) != null && !jsonObj.get(SERIALIZED_NAME_FINGERPRINT).isJsonNull()) && !jsonObj.get(
                SERIALIZED_NAME_FINGERPRINT).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `fingerprint` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_FINGERPRINT).toString()));
        }
        if (!jsonObj.get(SERIALIZED_NAME_UPLOADED_AT).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `uploaded_at` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_UPLOADED_AT).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_DOWNLOAD_URL) != null && !jsonObj.get(SERIALIZED_NAME_DOWNLOAD_URL).isJsonNull()) && !jsonObj.get(
                SERIALIZED_NAME_DOWNLOAD_URL).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `download_url` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_DOWNLOAD_URL).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_SECONDARY_DOWNLOAD_URL) != null && !jsonObj.get(SERIALIZED_NAME_SECONDARY_DOWNLOAD_URL).isJsonNull())
                && !jsonObj.get(
                SERIALIZED_NAME_SECONDARY_DOWNLOAD_URL).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `secondary_download_url` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_SECONDARY_DOWNLOAD_URL).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_INSTALL_URL) != null && !jsonObj.get(SERIALIZED_NAME_INSTALL_URL).isJsonNull()) && !jsonObj.get(
                SERIALIZED_NAME_INSTALL_URL).isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `install_url` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_INSTALL_URL).toString()));
        }
        if ((jsonObj.get(SERIALIZED_NAME_DESTINATION_TYPE) != null && !jsonObj.get(SERIALIZED_NAME_DESTINATION_TYPE).isJsonNull()) && !jsonObj.get(
                        SERIALIZED_NAME_DESTINATION_TYPE)
                .isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format("Expected the field `destination_type` to be a primitive type in the JSON string but got `%s`",
                            jsonObj.get(SERIALIZED_NAME_DESTINATION_TYPE).toString()));
        }
        if (jsonObj.get(SERIALIZED_NAME_DISTRIBUTION_GROUPS) != null && !jsonObj.get(SERIALIZED_NAME_DISTRIBUTION_GROUPS).isJsonNull()) {
            JsonArray jsonArraydistributionGroups = jsonObj.getAsJsonArray(SERIALIZED_NAME_DISTRIBUTION_GROUPS);
            if (jsonArraydistributionGroups != null && !jsonObj.get(SERIALIZED_NAME_DISTRIBUTION_GROUPS).isJsonArray()) {
                // ensure the json data is an array
                throw new IllegalArgumentException(
                        String.format("Expected the field `distribution_groups` to be an array in the JSON string but got `%s`",
                                jsonObj.get(SERIALIZED_NAME_DISTRIBUTION_GROUPS).toString()));
            }
        }
        if (jsonObj.get(SERIALIZED_NAME_DISTRIBUTION_STORES) != null && !jsonObj.get(SERIALIZED_NAME_DISTRIBUTION_STORES).isJsonNull()) {
            JsonArray jsonArraydistributionStores = jsonObj.getAsJsonArray(SERIALIZED_NAME_DISTRIBUTION_STORES);
            if (jsonArraydistributionStores != null && !jsonObj.get(SERIALIZED_NAME_DISTRIBUTION_STORES).isJsonArray()) {
                throw new IllegalArgumentException(
                        String.format("Expected the field `distribution_stores` to be an array in the JSON string but got `%s`",
                                jsonObj.get(SERIALIZED_NAME_DISTRIBUTION_STORES).toString()));
            }
        }
        if (jsonObj.get(SERIALIZED_NAME_DESTINATIONS) != null && !jsonObj.get(SERIALIZED_NAME_DESTINATIONS).isJsonNull()) {
            JsonArray jsonArraydestinations = jsonObj.getAsJsonArray(SERIALIZED_NAME_DESTINATIONS);
            if (jsonArraydestinations != null && !jsonObj.get(SERIALIZED_NAME_DESTINATIONS).isJsonArray()) {
                throw new IllegalArgumentException(
                        String.format("Expected the field `destinations` to be an array in the JSON string but got `%s`",
                                jsonObj.get(SERIALIZED_NAME_DESTINATIONS).toString()));
            }
        }
        if ((jsonObj.get(SERIALIZED_NAME_STATUS) != null && !jsonObj.get(SERIALIZED_NAME_STATUS).isJsonNull()) && !jsonObj.get(SERIALIZED_NAME_STATUS)
                .isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `status` to be a primitive type in the JSON string but got `%s`",
                    jsonObj.get(SERIALIZED_NAME_STATUS).toString()));
        }
    }

    /**
     * Create an instance of ReleaseDetailsResponse given an JSON string
     *
     * @param jsonString JSON string
     * @return An instance of ReleaseDetailsResponse
     */
    public static ReleaseDetailsResponse fromJson(String jsonString) {
        return JSON.getGson().fromJson(jsonString, ReleaseDetailsResponse.class);
    }

    public ReleaseDetailsResponse id(Integer id) {

        this.id = id;
        return this;
    }

    /**
     * ID identifying this unique release.
     *
     * @return id
     **/
    @javax.annotation.Nonnull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReleaseDetailsResponse appName(String appName) {

        this.appName = appName;
        return this;
    }

    /**
     * The app&#39;s name (extracted from the uploaded release).
     *
     * @return appName
     **/
    @javax.annotation.Nonnull
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public ReleaseDetailsResponse appDisplayName(String appDisplayName) {

        this.appDisplayName = appDisplayName;
        return this;
    }

    /**
     * The app&#39;s display name.
     *
     * @return appDisplayName
     **/
    @javax.annotation.Nonnull
    public String getAppDisplayName() {
        return appDisplayName;
    }

    public void setAppDisplayName(String appDisplayName) {
        this.appDisplayName = appDisplayName;
    }

    public ReleaseDetailsResponse appOs(String appOs) {

        this.appOs = appOs;
        return this;
    }

    /**
     * The app&#39;s OS.
     *
     * @return appOs
     **/
    @javax.annotation.Nullable
    public String getAppOs() {
        return appOs;
    }

    public void setAppOs(String appOs) {
        this.appOs = appOs;
    }

    public ReleaseDetailsResponse version(String version) {

        this.version = version;
        return this;
    }

    /**
     * The release&#39;s version.&lt;br&gt; For iOS: CFBundleVersion from info.plist. For Android: android:versionCode from AppManifest.xml.
     *
     * @return version
     **/
    @javax.annotation.Nonnull
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public ReleaseDetailsResponse origin(OriginEnum origin) {

        this.origin = origin;
        return this;
    }

    /**
     * The release&#39;s origin
     *
     * @return origin
     **/
    @javax.annotation.Nullable
    public OriginEnum getOrigin() {
        return origin;
    }

    public void setOrigin(OriginEnum origin) {
        this.origin = origin;
    }

    public ReleaseDetailsResponse shortVersion(String shortVersion) {

        this.shortVersion = shortVersion;
        return this;
    }

    /**
     * The release&#39;s short version.&lt;br&gt; For iOS: CFBundleShortVersionString from info.plist. For Android: android:versionName from AppManifest.xml.
     *
     * @return shortVersion
     **/
    @javax.annotation.Nonnull
    public String getShortVersion() {
        return shortVersion;
    }

    public void setShortVersion(String shortVersion) {
        this.shortVersion = shortVersion;
    }

    public ReleaseDetailsResponse releaseNotes(String releaseNotes) {

        this.releaseNotes = releaseNotes;
        return this;
    }

    /**
     * The release&#39;s release notes.
     *
     * @return releaseNotes
     **/
    @javax.annotation.Nullable
    public String getReleaseNotes() {
        return releaseNotes;
    }

    public void setReleaseNotes(String releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    public ReleaseDetailsResponse provisioningProfileName(String provisioningProfileName) {

        this.provisioningProfileName = provisioningProfileName;
        return this;
    }

    /**
     * The release&#39;s provisioning profile name.
     *
     * @return provisioningProfileName
     **/
    @javax.annotation.Nullable
    public String getProvisioningProfileName() {
        return provisioningProfileName;
    }

    public void setProvisioningProfileName(String provisioningProfileName) {
        this.provisioningProfileName = provisioningProfileName;
    }

    public ReleaseDetailsResponse provisioningProfileType(ProvisioningProfileTypeEnum provisioningProfileType) {

        this.provisioningProfileType = provisioningProfileType;
        return this;
    }

    /**
     * The type of the provisioning profile for the requested app version.
     *
     * @return provisioningProfileType
     **/
    @javax.annotation.Nullable
    public ProvisioningProfileTypeEnum getProvisioningProfileType() {
        return provisioningProfileType;
    }

    public void setProvisioningProfileType(ProvisioningProfileTypeEnum provisioningProfileType) {
        this.provisioningProfileType = provisioningProfileType;
    }

    public ReleaseDetailsResponse provisioningProfileExpiryDate(String provisioningProfileExpiryDate) {

        this.provisioningProfileExpiryDate = provisioningProfileExpiryDate;
        return this;
    }

    /**
     * expiration date of provisioning profile in UTC format.
     *
     * @return provisioningProfileExpiryDate
     **/
    @javax.annotation.Nullable
    public String getProvisioningProfileExpiryDate() {
        return provisioningProfileExpiryDate;
    }

    public void setProvisioningProfileExpiryDate(String provisioningProfileExpiryDate) {
        this.provisioningProfileExpiryDate = provisioningProfileExpiryDate;
    }

    public ReleaseDetailsResponse isProvisioningProfileSyncing(Boolean isProvisioningProfileSyncing) {

        this.isProvisioningProfileSyncing = isProvisioningProfileSyncing;
        return this;
    }

    /**
     * A flag that determines whether the release&#39;s provisioning profile is still extracted or not.
     *
     * @return isProvisioningProfileSyncing
     **/
    @javax.annotation.Nullable
    public Boolean getIsProvisioningProfileSyncing() {
        return isProvisioningProfileSyncing;
    }

    public void setIsProvisioningProfileSyncing(Boolean isProvisioningProfileSyncing) {
        this.isProvisioningProfileSyncing = isProvisioningProfileSyncing;
    }

    public ReleaseDetailsResponse size(Integer size) {

        this.size = size;
        return this;
    }

    /**
     * The release&#39;s size in bytes.
     *
     * @return size
     **/
    @javax.annotation.Nullable
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ReleaseDetailsResponse minOs(String minOs) {

        this.minOs = minOs;
        return this;
    }

    /**
     * The release&#39;s minimum required operating system.
     *
     * @return minOs
     **/
    @javax.annotation.Nullable
    public String getMinOs() {
        return minOs;
    }

    public void setMinOs(String minOs) {
        this.minOs = minOs;
    }

    public ReleaseDetailsResponse deviceFamily(String deviceFamily) {

        this.deviceFamily = deviceFamily;
        return this;
    }

    /**
     * The release&#39;s device family.
     *
     * @return deviceFamily
     **/
    @javax.annotation.Nullable
    public String getDeviceFamily() {
        return deviceFamily;
    }

    public void setDeviceFamily(String deviceFamily) {
        this.deviceFamily = deviceFamily;
    }

    public ReleaseDetailsResponse androidMinApiLevel(String androidMinApiLevel) {

        this.androidMinApiLevel = androidMinApiLevel;
        return this;
    }

    /**
     * The release&#39;s minimum required Android API level.
     *
     * @return androidMinApiLevel
     **/
    @javax.annotation.Nullable
    public String getAndroidMinApiLevel() {
        return androidMinApiLevel;
    }

    public void setAndroidMinApiLevel(String androidMinApiLevel) {
        this.androidMinApiLevel = androidMinApiLevel;
    }

    public ReleaseDetailsResponse bundleIdentifier(String bundleIdentifier) {

        this.bundleIdentifier = bundleIdentifier;
        return this;
    }

    /**
     * The identifier of the apps bundle.
     *
     * @return bundleIdentifier
     **/
    @javax.annotation.Nullable
    public String getBundleIdentifier() {
        return bundleIdentifier;
    }

    public void setBundleIdentifier(String bundleIdentifier) {
        this.bundleIdentifier = bundleIdentifier;
    }

    public ReleaseDetailsResponse packageHashes(List<String> packageHashes) {

        this.packageHashes = packageHashes;
        return this;
    }

    public ReleaseDetailsResponse addPackageHashesItem(String packageHashesItem) {
        if (this.packageHashes == null) {
            this.packageHashes = new ArrayList<>();
        }
        this.packageHashes.add(packageHashesItem);
        return this;
    }

    /**
     * Hashes for the packages.
     *
     * @return packageHashes
     **/
    @javax.annotation.Nullable
    public List<String> getPackageHashes() {
        return packageHashes;
    }

    public void setPackageHashes(List<String> packageHashes) {
        this.packageHashes = packageHashes;
    }

    public ReleaseDetailsResponse fingerprint(String fingerprint) {

        this.fingerprint = fingerprint;
        return this;
    }

    /**
     * MD5 checksum of the release binary.
     *
     * @return fingerprint
     **/
    @javax.annotation.Nullable
    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public ReleaseDetailsResponse uploadedAt(String uploadedAt) {

        this.uploadedAt = uploadedAt;
        return this;
    }

    /**
     * UTC time in ISO 8601 format of the uploaded time.
     *
     * @return uploadedAt
     **/
    @javax.annotation.Nonnull
    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public ReleaseDetailsResponse downloadUrl(String downloadUrl) {

        this.downloadUrl = downloadUrl;
        return this;
    }

    /**
     * The URL that hosts the binary for this release.
     *
     * @return downloadUrl
     **/
    @javax.annotation.Nullable
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public ReleaseDetailsResponse secondaryDownloadUrl(String secondaryDownloadUrl) {

        this.secondaryDownloadUrl = secondaryDownloadUrl;
        return this;
    }

    /**
     * The URL that hosts the secondary binary for this release, such as the apk file for aab releases.
     *
     * @return secondaryDownloadUrl
     **/
    @javax.annotation.Nullable
    public String getSecondaryDownloadUrl() {
        return secondaryDownloadUrl;
    }

    public void setSecondaryDownloadUrl(String secondaryDownloadUrl) {
        this.secondaryDownloadUrl = secondaryDownloadUrl;
    }

    public ReleaseDetailsResponse appIconUrl(String appIconUrl) {

        this.appIconUrl = appIconUrl;
        return this;
    }

    /**
     * A URL to the app&#39;s icon.
     *
     * @return appIconUrl
     **/
    @javax.annotation.Nonnull
    public String getAppIconUrl() {
        return appIconUrl;
    }

    public void setAppIconUrl(String appIconUrl) {
        this.appIconUrl = appIconUrl;
    }

    public ReleaseDetailsResponse installUrl(String installUrl) {

        this.installUrl = installUrl;
        return this;
    }

    /**
     * The href required to install a release on a mobile device. On iOS devices will be prefixed with &#x60;itms-services://?action&#x3D;download-manifest&amp;url&#x3D;&#x60;
     *
     * @return installUrl
     **/
    @javax.annotation.Nullable
    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public ReleaseDetailsResponse destinationType(DestinationTypeEnum destinationType) {

        this.destinationType = destinationType;
        return this;
    }

    /**
     * OBSOLETE. Will be removed in next version. The destination type.&lt;br&gt; &lt;b&gt;group&lt;/b&gt;: The release distributed to internal groups and distribution_groups details will be returned.&lt;br&gt; &lt;b&gt;store&lt;/b&gt;: The release distributed to external stores and distribution_stores details will be returned.&lt;br&gt; &lt;b&gt;tester&lt;/b&gt;: The release distributed testers details will be returned.&lt;br&gt;
     *
     * @return destinationType
     **/
    @javax.annotation.Nullable
    public DestinationTypeEnum getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(DestinationTypeEnum destinationType) {
        this.destinationType = destinationType;
    }

    public ReleaseDetailsResponse distributionGroups(List<Object> distributionGroups) {

        this.distributionGroups = distributionGroups;
        return this;
    }

    public ReleaseDetailsResponse addDistributionGroupsItem(
            Object distributionGroupsItem) {
        if (this.distributionGroups == null) {
            this.distributionGroups = new ArrayList<>();
        }
        this.distributionGroups.add(distributionGroupsItem);
        return this;
    }

    /**
     * OBSOLETE. Will be removed in next version. A list of distribution groups that are associated with this release.
     *
     * @return distributionGroups
     **/
    @javax.annotation.Nullable
    public List<Object> getDistributionGroups() {
        return distributionGroups;
    }

    public void setDistributionGroups(List<Object> distributionGroups) {
        this.distributionGroups = distributionGroups;
    }

    public ReleaseDetailsResponse distributionStores(List<Object> distributionStores) {

        this.distributionStores = distributionStores;
        return this;
    }

    public ReleaseDetailsResponse addDistributionStoresItem(
            Object distributionStoresItem) {
        if (this.distributionStores == null) {
            this.distributionStores = new ArrayList<>();
        }
        this.distributionStores.add(distributionStoresItem);
        return this;
    }

    /**
     * OBSOLETE. Will be removed in next version. A list of distribution stores that are associated with this release.
     *
     * @return distributionStores
     **/
    @javax.annotation.Nullable
    public List<Object> getDistributionStores() {
        return distributionStores;
    }

    public void setDistributionStores(List<Object> distributionStores) {
        this.distributionStores = distributionStores;
    }

    public ReleaseDetailsResponse destinations(List<Object> destinations) {

        this.destinations = destinations;
        return this;
    }

    public ReleaseDetailsResponse addDestinationsItem(Object destinationsItem) {
        if (this.destinations == null) {
            this.destinations = new ArrayList<>();
        }
        this.destinations.add(destinationsItem);
        return this;
    }

    /**
     * A list of distribution groups or stores.
     *
     * @return destinations
     **/
    @javax.annotation.Nullable
    public List<Object> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Object> destinations) {
        this.destinations = destinations;
    }

    public ReleaseDetailsResponse isUdidProvisioned(Boolean isUdidProvisioned) {

        this.isUdidProvisioned = isUdidProvisioned;
        return this;
    }

    /**
     * In calls that allow passing &#x60;udid&#x60; in the query string, this value will hold the provisioning status of that UDID in this release. Will be ignored for non-iOS platforms.
     *
     * @return isUdidProvisioned
     **/
    @javax.annotation.Nullable
    public Boolean getIsUdidProvisioned() {
        return isUdidProvisioned;
    }

    public void setIsUdidProvisioned(Boolean isUdidProvisioned) {
        this.isUdidProvisioned = isUdidProvisioned;
    }

    public ReleaseDetailsResponse canResign(Boolean canResign) {

        this.canResign = canResign;
        return this;
    }

    /**
     * In calls that allow passing &#x60;udid&#x60; in the query string, this value determines if a release can be re-signed. When true, after a re-sign, the tester will be able to install the release from his registered devices. Will not be returned for non-iOS platforms.
     *
     * @return canResign
     **/
    @javax.annotation.Nullable
    public Boolean getCanResign() {
        return canResign;
    }

    public void setCanResign(Boolean canResign) {
        this.canResign = canResign;
    }

    public ReleaseDetailsResponse build(Object build) {

        this.build = build;
        return this;
    }

    /**
     * Get build
     *
     * @return build
     **/
    @javax.annotation.Nullable
    public Object getBuild() {
        return build;
    }

    public void setBuild(Object build) {
        this.build = build;
    }

    public ReleaseDetailsResponse enabled(Boolean enabled) {

        this.enabled = enabled;
        return this;
    }

    /**
     * This value determines the whether a release currently is enabled or disabled.
     *
     * @return enabled
     **/
    @javax.annotation.Nonnull
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public ReleaseDetailsResponse status(String status) {

        this.status = status;
        return this;
    }

    /**
     * Status of the release.
     *
     * @return status
     **/
    @javax.annotation.Nullable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReleaseDetailsResponse isExternalBuild(Boolean isExternalBuild) {

        this.isExternalBuild = isExternalBuild;
        return this;
    }

    /**
     * This value determines if a release is external or not.
     *
     * @return isExternalBuild
     **/
    @javax.annotation.Nullable
    public Boolean getIsExternalBuild() {
        return isExternalBuild;
    }

    public void setIsExternalBuild(Boolean isExternalBuild) {
        this.isExternalBuild = isExternalBuild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReleaseDetailsResponse releaseDetailsResponse = (ReleaseDetailsResponse) o;
        return Objects.equals(this.id, releaseDetailsResponse.id) &&
                Objects.equals(this.appName, releaseDetailsResponse.appName) &&
                Objects.equals(this.appDisplayName, releaseDetailsResponse.appDisplayName) &&
                Objects.equals(this.appOs, releaseDetailsResponse.appOs) &&
                Objects.equals(this.version, releaseDetailsResponse.version) &&
                Objects.equals(this.origin, releaseDetailsResponse.origin) &&
                Objects.equals(this.shortVersion, releaseDetailsResponse.shortVersion) &&
                Objects.equals(this.releaseNotes, releaseDetailsResponse.releaseNotes) &&
                Objects.equals(this.fileExtension, releaseDetailsResponse.fileExtension) &&
                Objects.equals(this.provisioningProfileName, releaseDetailsResponse.provisioningProfileName) &&
                Objects.equals(this.provisioningProfileType, releaseDetailsResponse.provisioningProfileType) &&
                Objects.equals(this.provisioningProfileExpiryDate, releaseDetailsResponse.provisioningProfileExpiryDate) &&
                Objects.equals(this.isProvisioningProfileSyncing, releaseDetailsResponse.isProvisioningProfileSyncing) &&
                Objects.equals(this.size, releaseDetailsResponse.size) &&
                Objects.equals(this.minOs, releaseDetailsResponse.minOs) &&
                Objects.equals(this.deviceFamily, releaseDetailsResponse.deviceFamily) &&
                Objects.equals(this.androidMinApiLevel, releaseDetailsResponse.androidMinApiLevel) &&
                Objects.equals(this.bundleIdentifier, releaseDetailsResponse.bundleIdentifier) &&
                Objects.equals(this.packageHashes, releaseDetailsResponse.packageHashes) &&
                Objects.equals(this.fingerprint, releaseDetailsResponse.fingerprint) &&
                Objects.equals(this.uploadedAt, releaseDetailsResponse.uploadedAt) &&
                Objects.equals(this.downloadUrl, releaseDetailsResponse.downloadUrl) &&
                Objects.equals(this.secondaryDownloadUrl, releaseDetailsResponse.secondaryDownloadUrl) &&
                Objects.equals(this.appIconUrl, releaseDetailsResponse.appIconUrl) &&
                Objects.equals(this.installUrl, releaseDetailsResponse.installUrl) &&
                Objects.equals(this.destinationType, releaseDetailsResponse.destinationType) &&
                Objects.equals(this.distributionGroups, releaseDetailsResponse.distributionGroups) &&
                Objects.equals(this.distributionStores, releaseDetailsResponse.distributionStores) &&
                Objects.equals(this.destinations, releaseDetailsResponse.destinations) &&
                Objects.equals(this.isUdidProvisioned, releaseDetailsResponse.isUdidProvisioned) &&
                Objects.equals(this.canResign, releaseDetailsResponse.canResign) &&
                Objects.equals(this.build, releaseDetailsResponse.build) &&
                Objects.equals(this.enabled, releaseDetailsResponse.enabled) &&
                Objects.equals(this.status, releaseDetailsResponse.status) &&
                Objects.equals(this.isExternalBuild, releaseDetailsResponse.isExternalBuild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appName, appDisplayName, appOs, version, origin, shortVersion, releaseNotes, fileExtension, provisioningProfileName,
                provisioningProfileType, provisioningProfileExpiryDate, isProvisioningProfileSyncing, size, minOs, deviceFamily, androidMinApiLevel,
                bundleIdentifier, packageHashes, fingerprint, uploadedAt, downloadUrl, secondaryDownloadUrl, appIconUrl, installUrl, destinationType,
                distributionGroups, distributionStores, destinations, isUdidProvisioned, canResign, build, enabled, status, isExternalBuild);
    }

    @Override
    public String toString() {
        return "class ReleaseDetailsResponse {\n"
                + "    id: " + toIndentedString(id) + "\n"
                + "    appName: " + toIndentedString(appName) + "\n"
                + "    appDisplayName: " + toIndentedString(appDisplayName) + "\n"
                + "    appOs: " + toIndentedString(appOs) + "\n"
                + "    version: " + toIndentedString(version) + "\n"
                + "    origin: " + toIndentedString(origin) + "\n"
                + "    shortVersion: " + toIndentedString(shortVersion) + "\n"
                + "    releaseNotes: " + toIndentedString(releaseNotes) + "\n"
                + "    fileExtension: " + toIndentedString(fileExtension) + "\n"
                + "    provisioningProfileName: " + toIndentedString(provisioningProfileName) + "\n"
                + "    provisioningProfileType: " + toIndentedString(provisioningProfileType) + "\n"
                + "    provisioningProfileExpiryDate: " + toIndentedString(provisioningProfileExpiryDate) + "\n"
                + "    isProvisioningProfileSyncing: " + toIndentedString(isProvisioningProfileSyncing) + "\n"
                + "    size: " + toIndentedString(size) + "\n"
                + "    minOs: " + toIndentedString(minOs) + "\n"
                + "    deviceFamily: " + toIndentedString(deviceFamily) + "\n"
                + "    androidMinApiLevel: " + toIndentedString(androidMinApiLevel) + "\n"
                + "    bundleIdentifier: " + toIndentedString(bundleIdentifier) + "\n"
                + "    packageHashes: " + toIndentedString(packageHashes) + "\n"
                + "    fingerprint: " + toIndentedString(fingerprint) + "\n"
                + "    uploadedAt: " + toIndentedString(uploadedAt) + "\n"
                + "    downloadUrl: " + toIndentedString(downloadUrl) + "\n"
                + "    secondaryDownloadUrl: " + toIndentedString(secondaryDownloadUrl) + "\n"
                + "    appIconUrl: " + toIndentedString(appIconUrl) + "\n"
                + "    installUrl: " + toIndentedString(installUrl) + "\n"
                + "    destinationType: " + toIndentedString(destinationType) + "\n"
                + "    distributionGroups: " + toIndentedString(distributionGroups) + "\n"
                + "    distributionStores: " + toIndentedString(distributionStores) + "\n"
                + "    destinations: " + toIndentedString(destinations) + "\n"
                + "    isUdidProvisioned: " + toIndentedString(isUdidProvisioned) + "\n"
                + "    canResign: " + toIndentedString(canResign) + "\n"
                + "    build: " + toIndentedString(build) + "\n"
                + "    enabled: " + toIndentedString(enabled) + "\n"
                + "    status: " + toIndentedString(status) + "\n"
                + "    isExternalBuild: " + toIndentedString(isExternalBuild) + "\n"
                + "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    /**
     * Convert an instance of ReleaseDetailsResponse to an JSON string
     *
     * @return JSON string
     */
    public String toJson() {
        return JSON.getGson().toJson(this);
    }

    /**
     * The release&#39;s origin
     */
    @JsonAdapter(OriginEnum.Adapter.class)
    public enum OriginEnum {
        HOCKEYAPP("hockeyapp"),

        APPCENTER("appcenter");

        private final String value;

        OriginEnum(String value) {
            this.value = value;
        }

        public static OriginEnum fromValue(String value) {
            for (OriginEnum b : OriginEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException(String.format(UNEXPECTED_VALUE_ERROR_MESSAGE, value));
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static class Adapter extends TypeAdapter<OriginEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final OriginEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public OriginEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return OriginEnum.fromValue(value);
            }
        }
    }

    /**
     * The type of the provisioning profile for the requested app version.
     */
    @JsonAdapter(ProvisioningProfileTypeEnum.Adapter.class)
    public enum ProvisioningProfileTypeEnum {
        ADHOC("adhoc"),

        ENTERPRISE("enterprise"),

        OTHER("other");

        private final String value;

        ProvisioningProfileTypeEnum(String value) {
            this.value = value;
        }

        public static ProvisioningProfileTypeEnum fromValue(String value) {
            for (ProvisioningProfileTypeEnum b : ProvisioningProfileTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException(String.format(UNEXPECTED_VALUE_ERROR_MESSAGE, value));
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static class Adapter extends TypeAdapter<ProvisioningProfileTypeEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final ProvisioningProfileTypeEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public ProvisioningProfileTypeEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return ProvisioningProfileTypeEnum.fromValue(value);
            }
        }
    }

    /**
     * OBSOLETE. Will be removed in next version. The destination type.&lt;br&gt; &lt;b&gt;group&lt;/b&gt;: The release distributed to internal groups and distribution_groups details will be returned.&lt;br&gt; &lt;b&gt;store&lt;/b&gt;: The release distributed to external stores and distribution_stores details will be returned.&lt;br&gt; &lt;b&gt;tester&lt;/b&gt;: The release distributed testers details will be returned.&lt;br&gt;
     */
    @JsonAdapter(DestinationTypeEnum.Adapter.class)
    public enum DestinationTypeEnum {
        GROUP("group"),

        STORE("store"),

        TESTER("tester");

        private final String value;

        DestinationTypeEnum(String value) {
            this.value = value;
        }

        public static DestinationTypeEnum fromValue(String value) {
            for (DestinationTypeEnum b : DestinationTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException(String.format(UNEXPECTED_VALUE_ERROR_MESSAGE, value));
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static class Adapter extends TypeAdapter<DestinationTypeEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final DestinationTypeEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public DestinationTypeEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return DestinationTypeEnum.fromValue(value);
            }
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!ReleaseDetailsResponse.class.isAssignableFrom(type.getRawType())) {
                return null; // this class only serializes 'ReleaseDetailsResponse' and its subtypes
            }
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<ReleaseDetailsResponse> thisAdapter
                    = gson.getDelegateAdapter(this, TypeToken.get(ReleaseDetailsResponse.class));

            return (TypeAdapter<T>) new TypeAdapter<ReleaseDetailsResponse>() {
                @Override
                public void write(JsonWriter out, ReleaseDetailsResponse value) throws IOException {
                    JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
                    elementAdapter.write(out, obj);
                }

                @Override
                public ReleaseDetailsResponse read(JsonReader in) throws IOException {
                    JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
                    validateJsonObject(jsonObj);
                    return thisAdapter.fromJsonTree(jsonObj);
                }

            }.nullSafe();
        }
    }
}
