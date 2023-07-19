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
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class AppsAllOfOwner {
  private static final String UNEXPECTED_VALUE_ERROR_MESSAGE = "Unexpected value '%s'";
  public static final String SERIALIZED_NAME_ID = "id";
  public static final String SERIALIZED_NAME_AVATAR_URL = "avatar_url";
  public static final String SERIALIZED_NAME_DISPLAY_NAME = "display_name";
  public static final String SERIALIZED_NAME_EMAIL = "email";
  public static final String SERIALIZED_NAME_NAME = "name";
  public static final String SERIALIZED_NAME_TYPE = "type";
  private static final Set<String> OPENAPI_FIELDS;
  private static final Set<String> OPENAPI_REQUIRED_FIELDS;

  static {
    // a set of all properties/fields (JSON key names)
    OPENAPI_FIELDS = new HashSet<>();
    OPENAPI_FIELDS.add(SERIALIZED_NAME_ID);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_AVATAR_URL);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_DISPLAY_NAME);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_EMAIL);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_NAME);
    OPENAPI_FIELDS.add(SERIALIZED_NAME_TYPE);

    // a set of required properties/fields (JSON key names)
    OPENAPI_REQUIRED_FIELDS = new HashSet<>();
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_ID);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_DISPLAY_NAME);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_NAME);
    OPENAPI_REQUIRED_FIELDS.add(SERIALIZED_NAME_TYPE);
  }

  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;
  @SerializedName(SERIALIZED_NAME_AVATAR_URL)
  private String avatarUrl;
  @SerializedName(SERIALIZED_NAME_DISPLAY_NAME)
  private String displayName;
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;
  @SerializedName(SERIALIZED_NAME_TYPE)
  private TypeEnum type;

  public AppsAllOfOwner() {
    //empty
  }

  /**
   * Validates the JSON Object and throws an exception if issues found
   *
   * @param jsonObj JSON Object
   */
  public static void validateJsonObject(JsonObject jsonObj) {
    if (jsonObj == null && !AppsAllOfOwner.OPENAPI_REQUIRED_FIELDS.isEmpty()) {
      throw new IllegalArgumentException(
              String.format("The required field(s) %s in AppsGetForOrgUser200ResponseInnerAllOfOwner is not found in the empty JSON string",
                      AppsAllOfOwner.OPENAPI_REQUIRED_FIELDS));
    }

    Set<Entry<String, JsonElement>> entries = Objects.requireNonNull(jsonObj).entrySet();
    // check to see if the JSON string contains additional fields
    for (Entry<String, JsonElement> entry : entries) {
      if (!AppsAllOfOwner.OPENAPI_FIELDS.contains(entry.getKey())) {
        throw new IllegalArgumentException(String.format(
                "The field `%s` in the JSON string is not defined in the `AppsGetForOrgUser200ResponseInnerAllOfOwner` properties. JSON: %s",
                entry.getKey(), jsonObj));
      }
    }

    // check to make sure all required properties/fields are present in the JSON string
    for (String requiredField : AppsAllOfOwner.OPENAPI_REQUIRED_FIELDS) {
      if (jsonObj.get(requiredField) == null) {
        throw new IllegalArgumentException(
                String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj));
      }
    }
    if (!jsonObj.get(SERIALIZED_NAME_ID).isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `id` to be a primitive type in the JSON string but got `%s`",
                      jsonObj.get(SERIALIZED_NAME_ID).toString()));
    }
    if ((jsonObj.get(SERIALIZED_NAME_AVATAR_URL) != null && !jsonObj.get(SERIALIZED_NAME_AVATAR_URL).isJsonNull()) && !jsonObj.get(
            SERIALIZED_NAME_AVATAR_URL).isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `avatar_url` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_AVATAR_URL).toString()));
    }
    if (!jsonObj.get(SERIALIZED_NAME_DISPLAY_NAME).isJsonPrimitive()) {
      throw new IllegalArgumentException(
              String.format("Expected the field `display_name` to be a primitive type in the JSON string but got `%s`",
                      jsonObj.get(SERIALIZED_NAME_DISPLAY_NAME).toString()));
    }
    if ((jsonObj.get(SERIALIZED_NAME_EMAIL) != null && !jsonObj.get(SERIALIZED_NAME_EMAIL).isJsonNull()) && !jsonObj.get(SERIALIZED_NAME_EMAIL)
            .isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `email` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_EMAIL).toString()));
    }
    if (!jsonObj.get(SERIALIZED_NAME_NAME).isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_NAME).toString()));
    }
    if (!jsonObj.get(SERIALIZED_NAME_TYPE).isJsonPrimitive()) {
      throw new IllegalArgumentException(String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`",
              jsonObj.get(SERIALIZED_NAME_TYPE).toString()));
    }
  }

  /**
   * Create an instance of AppsGetForOrgUser200ResponseInnerAllOfOwner given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of AppsGetForOrgUser200ResponseInnerAllOfOwner
   */
  public static AppsAllOfOwner fromJson(String jsonString) {
    return JSON.getGson().fromJson(jsonString, AppsAllOfOwner.class);
  }

  public AppsAllOfOwner id(UUID id) {

    this.id = id;
    return this;
  }

  /**
   * The unique id (UUID) of the owner
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

  public AppsAllOfOwner avatarUrl(String avatarUrl) {

    this.avatarUrl = avatarUrl;
    return this;
  }

  /**
   * The avatar URL of the owner
   *
   * @return avatarUrl
   **/
  @javax.annotation.Nullable
  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public AppsAllOfOwner displayName(String displayName) {

    this.displayName = displayName;
    return this;
  }

  /**
   * The owner&#39;s display name
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

  public AppsAllOfOwner email(String email) {

    this.email = email;
    return this;
  }

  /**
   * The owner&#39;s email address
   *
   * @return email
   **/
  @javax.annotation.Nullable
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AppsAllOfOwner name(String name) {

    this.name = name;
    return this;
  }

  /**
   * The unique name that used to identify the owner
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

  public AppsAllOfOwner type(TypeEnum type) {

    this.type = type;
    return this;
  }

  /**
   * The owner type. Can either be &#39;org&#39; or &#39;user&#39;
   *
   * @return type
   **/
  @javax.annotation.Nonnull
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppsAllOfOwner appsAllOfOwner = (AppsAllOfOwner) o;
    return Objects.equals(this.id, appsAllOfOwner.id) &&
            Objects.equals(this.avatarUrl, appsAllOfOwner.avatarUrl) &&
            Objects.equals(this.displayName, appsAllOfOwner.displayName) &&
            Objects.equals(this.email, appsAllOfOwner.email) &&
            Objects.equals(this.name, appsAllOfOwner.name) &&
            Objects.equals(this.type, appsAllOfOwner.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, avatarUrl, displayName, email, name, type);
  }

  @Override
  public String toString() {
    return "class AppAllOfOwner {\n"
            + "    id: " + toIndentedString(id) + "\n"
            + "    avatarUrl: " + toIndentedString(avatarUrl) + "\n"
            + "    displayName: " + toIndentedString(displayName) + "\n"
            + "    email: " + toIndentedString(email) + "\n"
            + "    name: " + toIndentedString(name) + "\n"
            + "    type: " + toIndentedString(type) + "\n"
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
   * Convert an instance of AppsGetForOrgUser200ResponseInnerAllOfOwner to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }

  /**
   * The owner type. Can either be &#39;org&#39; or &#39;user&#39;
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    ORG("org"),

    USER("user");

    private final String value;

    TypeEnum(String value) {
      this.value = value;
    }

    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
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

    public static class Adapter extends TypeAdapter<TypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return TypeEnum.fromValue(value);
      }
    }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      if (!AppsAllOfOwner.class.isAssignableFrom(type.getRawType())) {
        return null; // this class only serializes 'AppsGetForOrgUser200ResponseInnerAllOfOwner' and its subtypes
      }
      final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
      final TypeAdapter<AppsAllOfOwner> thisAdapter
              = gson.getDelegateAdapter(this, TypeToken.get(AppsAllOfOwner.class));

      return (TypeAdapter<T>) new TypeAdapter<AppsAllOfOwner>() {
        @Override
        public void write(JsonWriter out, AppsAllOfOwner value) throws IOException {
          JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
          elementAdapter.write(out, obj);
        }

        @Override
        public AppsAllOfOwner read(JsonReader in) throws IOException {
          JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
          validateJsonObject(jsonObj);
          return thisAdapter.fromJsonTree(jsonObj);
        }

      }.nullSafe();
    }
  }
}
