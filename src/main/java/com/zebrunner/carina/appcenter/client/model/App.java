package com.zebrunner.carina.appcenter.client.model;

import com.google.gson.Gson;
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
import java.util.UUID;

public class App {
  private static final String UNEXPECTED_VALUE_ERROR_MESSAGE = "Unexpected value '%s'";
  private static final Set<String> OPENAPI_FIELDS;
  private static final Set<String> OPENAPI_REQUIRED_FIELDS;

  private static final String SERIALIZED_NAME_ID = "id";
  private static final String SERIALIZED_NAME_DESCRIPTION = "description";
  private static final String SERIALIZED_NAME_DISPLAY_NAME = "display_name";
  private static final String SERIALIZED_NAME_RELEAZE_TYPE = "release_type";
  private static final String SERIALIZED_NAME_ICON_URL = "icon_url";
  private static final String SERIALIZED_NAME_ICON_SOURCE = "icon_source";
  private static final String SERIALIZED_NAME_NAME = "name";
  private static final String SERIALIZED_NAME_OS = "os";
  private static final String SERIALIZED_NAME_OWNER = "owner";
  private static final String SERIALIZED_NAME_APP_SECRET = "app_secret";
  private static final String SERIALIZED_NAME_AZURE_SUBSCRIPTION = "azure_subscription";
  private static final String SERIALIZED_NAME_PLATFORM = "platform";
  private static final String SERIALIZED_NAME_ORIGIN = "origin";
  private static final String SERIALIZED_NAME_CREATED_AT = "created_at";
  private static final String SERIALIZED_NAME_UPDATED_AT = "updated_at";
  private static final String SERIALIZED_NAME_MEMBER_PERMISSIONS = "member_permissions";

  static {
    OPENAPI_FIELDS = new HashSet<>();
    OPENAPI_FIELDS.add(SERIALIZED_NAME_ID);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_DESCRIPTION);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_DISPLAY_NAME);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_RELEAZE_TYPE);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_ICON_URL);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_ICON_SOURCE);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_NAME);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_OS);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_OWNER);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_APP_SECRET);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_AZURE_SUBSCRIPTION);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_PLATFORM);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_ORIGIN);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_CREATED_AT);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_UPDATED_AT);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_MEMBER_PERMISSIONS);

    // a set of required properties/fields (JSON key names)
    OPENAPI_REQUIRED_FIELDS = new HashSet<>();
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_ID);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_DISPLAY_NAME);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_NAME);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_OS);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_OWNER);
  }

  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;
  @SerializedName(SERIALIZED_NAME_DISPLAY_NAME)
  private String displayName;
  @SerializedName(SERIALIZED_NAME_RELEAZE_TYPE)
  private String releaseType;
  @SerializedName(SERIALIZED_NAME_ICON_URL)
  private String iconUrl;
  @SerializedName(SERIALIZED_NAME_ICON_SOURCE)
  private String iconSource;
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;
  @SerializedName(SERIALIZED_NAME_OS)
  private OsEnum os;
  @SerializedName(SERIALIZED_NAME_OWNER)
  private AppsAllOfOwner owner;
  @SerializedName(SERIALIZED_NAME_APP_SECRET)
  private String appSecret;
  @SerializedName(SERIALIZED_NAME_AZURE_SUBSCRIPTION)
  private Object azureSubscription;
  @SerializedName(SERIALIZED_NAME_PLATFORM)
  private PlatformEnum platform;
  @SerializedName(SERIALIZED_NAME_ORIGIN)
  private OriginEnum origin;
  @SerializedName(SERIALIZED_NAME_CREATED_AT)
  private String createdAt;
  @SerializedName(SERIALIZED_NAME_UPDATED_AT)
  private String updatedAt;
  @SerializedName(SERIALIZED_NAME_MEMBER_PERMISSIONS)
  private List<MemberPermissionsEnum> memberPermissions;

  public App() {
    //empty
  }

  public static void validateJsonObject(JsonObject jsonObj) {
    if (jsonObj == null && !App.OPENAPI_REQUIRED_FIELDS.isEmpty()) {
      throw new IllegalArgumentException(
              String.format("The required field(s) %s in App is not found in the empty JSON string",
                      App.OPENAPI_REQUIRED_FIELDS));
    }

    Set<Entry<String, JsonElement>> entries = Objects.requireNonNull(jsonObj).entrySet();
    // check to see if the JSON string contains additional fields
    for (Entry<String, JsonElement> entry : entries) {
      if (!App.OPENAPI_FIELDS.contains(entry.getKey())) {
        throw new IllegalArgumentException(String.format(
                "The field `%s` in the JSON string is not defined in the `App` properties. JSON: %s",
                entry.getKey(), jsonObj));
      }
    }

    // check to make sure all required properties/fields are present in the JSON string
    for (String requiredField : App.OPENAPI_REQUIRED_FIELDS) {
      if (jsonObj.get(requiredField) == null) {
        throw new IllegalArgumentException(
                String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj));
      }
    }
    if (!jsonObj.get("id").isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("id").toString()));
    }
    if ((jsonObj.get(SERIALIZED_NAME_DESCRIPTION) != null && !jsonObj.get(SERIALIZED_NAME_DESCRIPTION).isJsonNull()) && !jsonObj.get(
            SERIALIZED_NAME_DESCRIPTION).isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `description` to be a primitive type in the JSON string but got `%s`",
                      jsonObj.get(SERIALIZED_NAME_DESCRIPTION).toString()));
    }
    if (!jsonObj.get(SERIALIZED_NAME_DISPLAY_NAME).isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `display_name` to be a primitive type in the JSON string but got `%s`",
                      jsonObj.get(SERIALIZED_NAME_DISPLAY_NAME).toString()));
    }
    if ((jsonObj.get(SERIALIZED_NAME_RELEAZE_TYPE) != null && !jsonObj.get(SERIALIZED_NAME_RELEAZE_TYPE).isJsonNull()) && !jsonObj.get(
                    SERIALIZED_NAME_RELEAZE_TYPE)
            .isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `release_type` to be a primitive type in the JSON string but got `%s`",
                      jsonObj.get(SERIALIZED_NAME_RELEAZE_TYPE).toString()));
    }
    if ((jsonObj.get(SERIALIZED_NAME_ICON_URL) != null && !jsonObj.get(SERIALIZED_NAME_ICON_URL).isJsonNull()) && !jsonObj.get(
            SERIALIZED_NAME_ICON_URL).isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `icon_url` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_ICON_URL).toString()));
    }
    if ((jsonObj.get(SERIALIZED_NAME_ICON_SOURCE) != null && !jsonObj.get(SERIALIZED_NAME_ICON_SOURCE).isJsonNull()) && !jsonObj.get(
            SERIALIZED_NAME_ICON_SOURCE).isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `icon_source` to be a primitive type in the JSON string but got `%s`",
                      jsonObj.get(SERIALIZED_NAME_ICON_SOURCE).toString()));
    }
    if (!jsonObj.get(SERIALIZED_NAME_NAME).isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_NAME).toString()));
    }
    if (!jsonObj.get(SERIALIZED_NAME_OS).isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `os` to be a primitive type in the JSON string but got `%s`", jsonObj.get("os").toString()));
    }
    // validate the required field `owner`
    AppsAllOfOwner.validateJsonObject(jsonObj.getAsJsonObject(SERIALIZED_NAME_OWNER));
    if ((jsonObj.get(SERIALIZED_NAME_APP_SECRET) != null && !jsonObj.get(SERIALIZED_NAME_APP_SECRET).isJsonNull()) && !jsonObj.get(
            SERIALIZED_NAME_APP_SECRET).isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `app_secret` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_APP_SECRET).toString()));
    }

    if ((jsonObj.get(SERIALIZED_NAME_PLATFORM) != null && !jsonObj.get(SERIALIZED_NAME_PLATFORM).isJsonNull()) && !jsonObj.get(
            SERIALIZED_NAME_PLATFORM).isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `platform` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_PLATFORM).toString()));
    }
    if ((jsonObj.get(SERIALIZED_NAME_ORIGIN) != null && !jsonObj.get(SERIALIZED_NAME_ORIGIN).isJsonNull()) && !jsonObj.get(SERIALIZED_NAME_ORIGIN)
            .isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `origin` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_ORIGIN).toString()));
    }
    if ((jsonObj.get(SERIALIZED_NAME_CREATED_AT) != null && !jsonObj.get(SERIALIZED_NAME_CREATED_AT).isJsonNull()) && !jsonObj.get(
            SERIALIZED_NAME_CREATED_AT).isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `created_at` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_CREATED_AT).toString()));
    }
    if ((jsonObj.get(SERIALIZED_NAME_UPDATED_AT) != null && !jsonObj.get(SERIALIZED_NAME_UPDATED_AT).isJsonNull()) && !jsonObj.get(
            SERIALIZED_NAME_UPDATED_AT).isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `updated_at` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_UPDATED_AT).toString()));
    }
    // ensure the optional json data is an array if present
    if (jsonObj.get(SERIALIZED_NAME_MEMBER_PERMISSIONS) != null && !jsonObj.get(SERIALIZED_NAME_MEMBER_PERMISSIONS).isJsonArray()) {
      throw new IllegalArgumentException(String.format("Expected the field `member_permissions` to be an array in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_MEMBER_PERMISSIONS).toString()));
    }
  }

  public static App fromJson(String jsonString) {
    return JSON.getGson().fromJson(jsonString, App.class);
  }

  public App id(UUID id) {

    this.id = id;
    return this;
  }

  /**
   * The unique ID (UUID) of the app
   *
   * @return id
   **/
  @javax.annotation.Nonnull
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public App description(String description) {

    this.description = description;
    return this;
  }

  /**
   * The description of the app
   *
   * @return description
   **/
  @javax.annotation.Nullable
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public App displayName(String displayName) {

    this.displayName = displayName;
    return this;
  }

  /**
   * The display name of the app
   *
   * @return displayName
   **/
  @javax.annotation.Nonnull
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public App releaseType(String releaseType) {

    this.releaseType = releaseType;
    return this;
  }

  /**
   * A one-word descriptive release-type value that starts with a capital letter but is otherwise lowercase
   *
   * @return releaseType
   **/
  @javax.annotation.Nullable
  public String getReleaseType() {
    return releaseType;
  }

  public void setReleaseType(String releaseType) {
    this.releaseType = releaseType;
  }

  public App iconUrl(String iconUrl) {

    this.iconUrl = iconUrl;
    return this;
  }

  /**
   * The string representation of the URL pointing to the app&#39;s icon
   *
   * @return iconUrl
   **/
  @javax.annotation.Nullable
  public String getIconUrl() {
    return iconUrl;
  }

  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }

  public App iconSource(String iconSource) {

    this.iconSource = iconSource;
    return this;
  }

  /**
   * The string representation of the source of the app&#39;s icon
   *
   * @return iconSource
   **/
  @javax.annotation.Nullable
  public String getIconSource() {
    return iconSource;
  }

  public void setIconSource(String iconSource) {
    this.iconSource = iconSource;
  }

  public App name(String name) {

    this.name = name;
    return this;
  }

  /**
   * The name of the app used in URLs
   *
   * @return name
   **/
  @javax.annotation.Nonnull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public App os(OsEnum os) {

    this.os = os;
    return this;
  }

  /**
   * The OS the app will be running on
   *
   * @return os
   **/
  @javax.annotation.Nonnull
  public OsEnum getOs() {
    return os;
  }

  public void setOs(OsEnum os) {
    this.os = os;
  }

  public App owner(AppsAllOfOwner owner) {

    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   *
   * @return owner
   **/
  @javax.annotation.Nonnull
  public AppsAllOfOwner getOwner() {
    return owner;
  }

  public void setOwner(AppsAllOfOwner owner) {
    this.owner = owner;
  }

  public App appSecret(String appSecret) {

    this.appSecret = appSecret;
    return this;
  }

  /**
   * A unique and secret key used to identify the app in communication with the ingestion endpoint for crash reporting and analytics
   *
   * @return appSecret
   **/
  @javax.annotation.Nullable
  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
  }

  public App azureSubscription(Object azureSubscription) {

    this.azureSubscription = azureSubscription;
    return this;
  }

  @javax.annotation.Nullable
  public Object getAzureSubscription() {
    return azureSubscription;
  }

  public void setAzureSubscription(Object azureSubscription) {
    this.azureSubscription = azureSubscription;
  }

  public App platform(PlatformEnum platform) {

    this.platform = platform;
    return this;
  }

  /**
   * The platform of the app
   *
   * @return platform
   **/
  @javax.annotation.Nullable
  public PlatformEnum getPlatform() {
    return platform;
  }

  public void setPlatform(PlatformEnum platform) {
    this.platform = platform;
  }

  public App origin(OriginEnum origin) {

    this.origin = origin;
    return this;
  }

  /**
   * The creation origin of this app
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

  public App createdAt(String createdAt) {

    this.createdAt = createdAt;
    return this;
  }

  /**
   * The created date of this app
   *
   * @return createdAt
   **/
  @javax.annotation.Nullable
  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public App updatedAt(String updatedAt) {

    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * The last updated date of this app
   *
   * @return updatedAt
   **/
  @javax.annotation.Nullable
  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public App memberPermissions(List<MemberPermissionsEnum> memberPermissions) {

    this.memberPermissions = memberPermissions;
    return this;
  }

  public App addMemberPermissionsItem(MemberPermissionsEnum memberPermissionsItem) {
    if (this.memberPermissions == null) {
      this.memberPermissions = new ArrayList<>();
    }
    this.memberPermissions.add(memberPermissionsItem);
    return this;
  }

  /**
   * The permissions of the calling user
   *
   * @return memberPermissions
   **/
  @javax.annotation.Nullable
  public List<MemberPermissionsEnum> getMemberPermissions() {
    return memberPermissions;
  }

  public void setMemberPermissions(List<MemberPermissionsEnum> memberPermissions) {
    this.memberPermissions = memberPermissions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    App app = (App) o;
    return Objects.equals(this.id, app.id) &&
            Objects.equals(this.description, app.description) &&
            Objects.equals(this.displayName, app.displayName) &&
            Objects.equals(this.releaseType, app.releaseType) &&
            Objects.equals(this.iconUrl, app.iconUrl) &&
            Objects.equals(this.iconSource, app.iconSource) &&
            Objects.equals(this.name, app.name) &&
            Objects.equals(this.os, app.os) &&
            Objects.equals(this.owner, app.owner) &&
            Objects.equals(this.appSecret, app.appSecret) &&
            Objects.equals(this.azureSubscription, app.azureSubscription) &&
            Objects.equals(this.platform, app.platform) &&
            Objects.equals(this.origin, app.origin) &&
            Objects.equals(this.createdAt, app.createdAt) &&
            Objects.equals(this.updatedAt, app.updatedAt) &&
            Objects.equals(this.memberPermissions, app.memberPermissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, displayName, releaseType, iconUrl, iconSource, name, os, owner, appSecret, azureSubscription, platform,
            origin, createdAt, updatedAt, memberPermissions);
  }

  @Override
  public String toString() {
    return "class App {\n"
            + "    id: " + toIndentedString(id) + "\n"
            + "    description: " + toIndentedString(description) + "\n"
            + "    displayName: " + toIndentedString(displayName) + "\n"
            + "    releaseType: " + toIndentedString(releaseType) + "\n"
            + "    iconUrl: " + toIndentedString(iconUrl) + "\n"
            + "    iconSource: " + toIndentedString(iconSource) + "\n"
            + "    name: " + toIndentedString(name) + "\n"
            + "    os: " + toIndentedString(os) + "\n"
            + "    owner: " + toIndentedString(owner) + "\n"
            + "    appSecret: " + toIndentedString(appSecret) + "\n"
            + "    azureSubscription: " + toIndentedString(azureSubscription) + "\n"
            + "    platform: " + toIndentedString(platform) + "\n"
            + "    origin: " + toIndentedString(origin) + "\n"
            + "    createdAt: " + toIndentedString(createdAt) + "\n"
            + "    updatedAt: " + toIndentedString(updatedAt) + "\n"
            + "    memberPermissions: " + toIndentedString(memberPermissions) + "\n"
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
   * Convert an instance of App to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }

  /**
   * The OS the app will be running on
   */
  @JsonAdapter(OsEnum.Adapter.class)
  public enum OsEnum {
    ANDROID("Android"),

    IOS("iOS"),

    MACOS("macOS"),

    TIZEN("Tizen"),

    TVOS("tvOS"),

    WINDOWS("Windows"),

    LINUX("Linux"),

    CUSTOM("Custom");

    private final String value;

    OsEnum(String value) {
      this.value = value;
    }

    public static OsEnum fromValue(String value) {
      for (OsEnum b : OsEnum.values()) {
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

    public static class Adapter extends TypeAdapter<OsEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final OsEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OsEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return OsEnum.fromValue(value);
      }
    }
  }

  /**
   * The platform of the app
   */
  @JsonAdapter(PlatformEnum.Adapter.class)
  public enum PlatformEnum {
    JAVA("Java"),

    OBJECTIVE_C_SWIFT("Objective-C-Swift"),

    UWP("UWP"),

    CORDOVA("Cordova"),

    REACT_NATIVE("React-Native"),

    UNITY("Unity"),

    ELECTRON("Electron"),

    XAMARIN("Xamarin"),

    WPF("WPF"),

    WINFORMS("WinForms"),

    UNKNOWN("Unknown"),

    CUSTOM("Custom");

    private final String value;

    PlatformEnum(String value) {
      this.value = value;
    }

    public static PlatformEnum fromValue(String value) {
      for (PlatformEnum b : PlatformEnum.values()) {
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

    public static class Adapter extends TypeAdapter<PlatformEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final PlatformEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public PlatformEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return PlatformEnum.fromValue(value);
      }
    }
  }

  /**
   * The creation origin of this app
   */
  @JsonAdapter(OriginEnum.Adapter.class)
  public enum OriginEnum {
    APPCENTER("appcenter"),

    HOCKEYAPP("hockeyapp"),

    CODEPUSH("codepush");

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
   * Gets or Sets memberPermissions
   */
  @JsonAdapter(MemberPermissionsEnum.Adapter.class)
  public enum MemberPermissionsEnum {
    MANAGER("manager"),

    DEVELOPER("developer"),

    VIEWER("viewer"),

    TESTER("tester");

    private final String value;

    MemberPermissionsEnum(String value) {
      this.value = value;
    }

    public static MemberPermissionsEnum fromValue(String value) {
      for (MemberPermissionsEnum b : MemberPermissionsEnum.values()) {
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

    public static class Adapter extends TypeAdapter<MemberPermissionsEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final MemberPermissionsEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public MemberPermissionsEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return MemberPermissionsEnum.fromValue(value);
      }
    }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      if (!App.class.isAssignableFrom(type.getRawType())) {
        return null; // this class only serializes 'App' and its subtypes
      }
      final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
      final TypeAdapter<App> thisAdapter
              = gson.getDelegateAdapter(this, TypeToken.get(App.class));

      return (TypeAdapter<T>) new TypeAdapter<App>() {
        @Override
        public void write(JsonWriter out, App value) throws IOException {
          JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
          elementAdapter.write(out, obj);
        }

        @Override
        public App read(JsonReader in) throws IOException {
          JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
          validateJsonObject(jsonObj);
          return thisAdapter.fromJsonTree(jsonObj);
        }

      }.nullSafe();
    }
  }
}

