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

public class ReleasesAvailableToTester {
  public static final String SERIALIZED_NAME_ID = "id";
  public static final String SERIALIZED_NAME_VERSION = "version";
  public static final String SERIALIZED_NAME_ORIGIN = "origin";
  public static final String SERIALIZED_NAME_SHORT_VERSION = "short_version";
  public static final String SERIALIZED_NAME_ENABLED = "enabled";
  public static final String SERIALIZED_NAME_UPLOADED_AT = "uploaded_at";
  public static final String SERIALIZED_NAME_DESTINATION_TYPE = "destination_type";
  public static final String SERIALIZED_NAME_DISTRIBUTION_GROUPS = "distribution_groups";
  public static final String SERIALIZED_NAME_DISTRIBUTION_STORES = "distribution_stores";
  public static final String SERIALIZED_NAME_DESTINATIONS = "destinations";
  public static final String SERIALIZED_NAME_BUILD = "build";
  public static final String SERIALIZED_NAME_IS_EXTERNAL_BUILD = "is_external_build";
  public static final String SERIALIZED_NAME_FILE_EXTENSION = "file_extension";
  private static final Set<String> OPENAPI_FIELDS;
  private static final Set<String> OPENAPI_REQUIRED_FIELDS;

  static {
    // a set of all properties/fields (JSON key names)
    OPENAPI_FIELDS = new HashSet<>();
    OPENAPI_FIELDS.add(SERIALIZED_NAME_ID);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_VERSION);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_ORIGIN);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_SHORT_VERSION);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_ENABLED);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_UPLOADED_AT);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_DESTINATION_TYPE);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_DISTRIBUTION_GROUPS);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_DISTRIBUTION_STORES);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_DESTINATIONS);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_BUILD);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_IS_EXTERNAL_BUILD);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_FILE_EXTENSION);

    // a set of required properties/fields (JSON key names)
    OPENAPI_REQUIRED_FIELDS = new HashSet<>();
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_ID);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_VERSION);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_SHORT_VERSION);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_ENABLED);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_UPLOADED_AT);
  }

  @SerializedName(SERIALIZED_NAME_ID)
  private Integer id;
  @SerializedName(SERIALIZED_NAME_VERSION)
  private String version;
  @SerializedName(SERIALIZED_NAME_ORIGIN)
  private OriginEnum origin;
  @SerializedName(SERIALIZED_NAME_SHORT_VERSION)
  private String shortVersion;
  @SerializedName(SERIALIZED_NAME_ENABLED)
  private Boolean enabled;
  @SerializedName(SERIALIZED_NAME_UPLOADED_AT)
  private String uploadedAt;
  @SerializedName(SERIALIZED_NAME_DESTINATION_TYPE)
  private DestinationTypeEnum destinationType;
  @SerializedName(SERIALIZED_NAME_DISTRIBUTION_GROUPS)
  private List<Object> distributionGroups;
  @SerializedName(SERIALIZED_NAME_DISTRIBUTION_STORES)
  private List<Object> distributionStores;
  @SerializedName(SERIALIZED_NAME_DESTINATIONS)
  private List<Object> destinations;
  @SerializedName(SERIALIZED_NAME_BUILD)
  private Object build;
  @SerializedName(SERIALIZED_NAME_IS_EXTERNAL_BUILD)
  private Boolean isExternalBuild;
  @SerializedName(SERIALIZED_NAME_FILE_EXTENSION)
  private String fileExtension;

  public ReleasesAvailableToTester() {
    //empty
  }

  /**
   * Validates the JSON Object and throws an exception if issues found
   *
   * @param jsonObj JSON Object
   */
  public static void validateJsonObject(JsonObject jsonObj) {
    if (jsonObj == null && !ReleasesAvailableToTester.OPENAPI_REQUIRED_FIELDS.isEmpty()) {
      throw new IllegalArgumentException(
              String.format("The required field(s) %s in ReleasesAvailableToTester is not found in the empty JSON string",
                      ReleasesAvailableToTester.OPENAPI_REQUIRED_FIELDS));
    }

    Set<Entry<String, JsonElement>> entries = Objects.requireNonNull(jsonObj).entrySet();
    // check to see if the JSON string contains additional fields
    for (Entry<String, JsonElement> entry : entries) {
      if (!ReleasesAvailableToTester.OPENAPI_FIELDS.contains(entry.getKey())) {
        throw new IllegalArgumentException(String.format(
                "The field `%s` in the JSON string is not defined in the `ReleasesAvailableToTester` properties. JSON: %s",
                entry.getKey(), jsonObj));
      }
    }

    // check to make sure all required properties/fields are present in the JSON string
    for (String requiredField : ReleasesAvailableToTester.OPENAPI_REQUIRED_FIELDS) {
      if (jsonObj.get(requiredField) == null) {
        throw new IllegalArgumentException(
                String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj));
      }
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
    if (!jsonObj.get(SERIALIZED_NAME_UPLOADED_AT).isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `uploaded_at` to be a primitive type in the JSON string but got `%s`",
                      jsonObj.get(SERIALIZED_NAME_UPLOADED_AT).toString()));
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

    if ((jsonObj.get(SERIALIZED_NAME_FILE_EXTENSION) != null && !jsonObj.get(SERIALIZED_NAME_FILE_EXTENSION).isJsonNull()) && !jsonObj.get(
                    SERIALIZED_NAME_FILE_EXTENSION)
            .isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `file_extension` to be a primitive type in the JSON string but got `%s`",
                      jsonObj.get(SERIALIZED_NAME_FILE_EXTENSION).toString()));
    }
  }

  /**
   * Create an instance of ReleasesAvailableToTester given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of ReleasesAvailableToTester
   */
  public static ReleasesAvailableToTester fromJson(String jsonString) {
    return JSON.getGson().fromJson(jsonString, ReleasesAvailableToTester.class);
  }

  public ReleasesAvailableToTester id(Integer id) {

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

  public ReleasesAvailableToTester version(String version) {

    this.version = version;
    return this;
  }

  /**
   * The release&#39;s version.&lt;br&gt; For iOS: CFBundleVersion from info.plist.&lt;br&gt; For Android: android:versionCode from AppManifest.xml.
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

  public ReleasesAvailableToTester origin(OriginEnum origin) {

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

  public ReleasesAvailableToTester shortVersion(String shortVersion) {

    this.shortVersion = shortVersion;
    return this;
  }

  /**
   * The release&#39;s short version.&lt;br&gt; For iOS: CFBundleShortVersionString from info.plist.&lt;br&gt; For Android: android:versionName from AppManifest.xml.
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

  public ReleasesAvailableToTester enabled(Boolean enabled) {

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

  public ReleasesAvailableToTester uploadedAt(String uploadedAt) {

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

  public ReleasesAvailableToTester destinationType(DestinationTypeEnum destinationType) {

    this.destinationType = destinationType;
    return this;
  }

  /**
   * OBSOLETE. Will be removed in next version. The destination type.&lt;br&gt; &lt;b&gt;group&lt;/b&gt;: The release distributed to internal groups and distribution_groups details will be returned.&lt;br&gt; &lt;b&gt;store&lt;/b&gt;: The release distributed to external stores and distribution_stores details will be returned. &lt;br&gt;
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

  public ReleasesAvailableToTester distributionGroups(
          List<Object> distributionGroups) {

    this.distributionGroups = distributionGroups;
    return this;
  }

  public ReleasesAvailableToTester addDistributionGroupsItem(
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

  public ReleasesAvailableToTester distributionStores(
          List<Object> distributionStores) {

    this.distributionStores = distributionStores;
    return this;
  }

  public ReleasesAvailableToTester addDistributionStoresItem(
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

  public ReleasesAvailableToTester destinations(List<Object> destinations) {

    this.destinations = destinations;
    return this;
  }

  public ReleasesAvailableToTester addDestinationsItem(
          Object destinationsItem) {
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

  public ReleasesAvailableToTester build(Object build) {

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

  public ReleasesAvailableToTester isExternalBuild(Boolean isExternalBuild) {

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

  public ReleasesAvailableToTester fileExtension(String fileExtension) {

    this.fileExtension = fileExtension;
    return this;
  }

  /**
   * The file extension of the main (user-uploaded) package file.
   *
   * @return fileExtension
   **/
  @javax.annotation.Nullable
  public String getFileExtension() {
    return fileExtension;
  }

  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReleasesAvailableToTester releasesAvailableToTester = (ReleasesAvailableToTester) o;
    return Objects.equals(this.id, releasesAvailableToTester.id) &&
            Objects.equals(this.version, releasesAvailableToTester.version) &&
            Objects.equals(this.origin, releasesAvailableToTester.origin) &&
            Objects.equals(this.shortVersion, releasesAvailableToTester.shortVersion) &&
            Objects.equals(this.enabled, releasesAvailableToTester.enabled) &&
            Objects.equals(this.uploadedAt, releasesAvailableToTester.uploadedAt) &&
            Objects.equals(this.destinationType, releasesAvailableToTester.destinationType) &&
            Objects.equals(this.distributionGroups, releasesAvailableToTester.distributionGroups) &&
            Objects.equals(this.distributionStores, releasesAvailableToTester.distributionStores) &&
            Objects.equals(this.destinations, releasesAvailableToTester.destinations) &&
            Objects.equals(this.build, releasesAvailableToTester.build) &&
            Objects.equals(this.isExternalBuild, releasesAvailableToTester.isExternalBuild) &&
            Objects.equals(this.fileExtension, releasesAvailableToTester.fileExtension);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, version, origin, shortVersion, enabled, uploadedAt, destinationType, distributionGroups, distributionStores,
            destinations, build, isExternalBuild, fileExtension);
  }

  @Override
  public String toString() {
    return "class ReleasesAvailableToTester {\n"
            + "    id: " + toIndentedString(id) + "\n"
            + "    version: " + toIndentedString(version) + "\n"
            + "    origin: " + toIndentedString(origin) + "\n"
            + "    shortVersion: " + toIndentedString(shortVersion) + "\n"
            + "    enabled: " + toIndentedString(enabled) + "\n"
            + "    uploadedAt: " + toIndentedString(uploadedAt) + "\n"
            + "    destinationType: " + toIndentedString(destinationType) + "\n"
            + "    distributionGroups: " + toIndentedString(distributionGroups) + "\n"
            + "    distributionStores: " + toIndentedString(distributionStores) + "\n"
            + "    destinations: " + toIndentedString(destinations) + "\n"
            + "    build: " + toIndentedString(build) + "\n"
            + "    isExternalBuild: " + toIndentedString(isExternalBuild) + "\n"
            + "    fileExtension: " + toIndentedString(fileExtension) + "\n"
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
   * Convert an instance of ReleasesAvailableToTester to an JSON string
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
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
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
   * OBSOLETE. Will be removed in next version. The destination type.&lt;br&gt; &lt;b&gt;group&lt;/b&gt;: The release distributed to internal groups and distribution_groups details will be returned.&lt;br&gt; &lt;b&gt;store&lt;/b&gt;: The release distributed to external stores and distribution_stores details will be returned. &lt;br&gt;
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
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
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
      if (!ReleasesAvailableToTester.class.isAssignableFrom(type.getRawType())) {
        return null; // this class only serializes 'ReleasesAvailableToTester' and its subtypes
      }
      final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
      final TypeAdapter<ReleasesAvailableToTester> thisAdapter
              = gson.getDelegateAdapter(this, TypeToken.get(ReleasesAvailableToTester.class));

      return (TypeAdapter<T>) new TypeAdapter<ReleasesAvailableToTester>() {
        @Override
        public void write(JsonWriter out, ReleasesAvailableToTester value) throws IOException {
          JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
          elementAdapter.write(out, obj);
        }

        @Override
        public ReleasesAvailableToTester read(JsonReader in) throws IOException {
          JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
          validateJsonObject(jsonObj);
          return thisAdapter.fromJsonTree(jsonObj);
        }

      }.nullSafe();
    }
  }
}

