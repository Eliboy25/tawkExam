package com.gp.tawk.core.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "Profile")
data class GitUserEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("login")
    @ColumnInfo val login: String? = null,
    @SerializedName("node_id")
    @ColumnInfo val node_id: String? = null,
    @SerializedName("avatar_url")
    @ColumnInfo val avatar_url: String? = null,
    @SerializedName("gravatar_id")
    @ColumnInfo val gravatar_id: String? = null,
    @SerializedName("url")
    @ColumnInfo val url: String? = null,
    @SerializedName("html_url")
    @ColumnInfo val html_url: String? = null,
    @SerializedName("followers_url")
    @ColumnInfo val followers_url: String? = null,
    @SerializedName("following_url")
    @ColumnInfo val following_url: String? = null,
    @SerializedName("gists_url")
    @ColumnInfo val gists_url: String? = null,
    @SerializedName("starred_url")
    @ColumnInfo val starred_url: String? = null,
    @SerializedName("subscriptions_url")
    @ColumnInfo val subscriptions_url: String? = null,
    @SerializedName("organizations_url")
    @ColumnInfo val organizations_url: String? = null,
    @SerializedName("repos_url")
    @ColumnInfo val repos_url: String? = null,
    @SerializedName("events_url")
    @ColumnInfo val events_url: String? = null,
    @SerializedName("received_events_url")
    @ColumnInfo val received_events_url: String? = null,
    @SerializedName("type")
    @ColumnInfo val type: String? = null,
    @SerializedName("site_admin")
    @ColumnInfo val site_admin: Boolean? = null,
    @SerializedName("name")
    @ColumnInfo val name: String? = null,
    @SerializedName("company")
    @ColumnInfo val company: String? = null,
    @SerializedName("blog")
    @ColumnInfo val blog: String? = null,
    @SerializedName("location")
    @ColumnInfo val location: String? = null,
    @SerializedName("email")
    @ColumnInfo val email: String? = null,
    @SerializedName("hireable")
    @ColumnInfo val hireable: String? = null,
    @SerializedName("bio")
    @ColumnInfo val bio: String? = null,
    @SerializedName("twitter_username")
    @ColumnInfo val twitter_username: String? = null,
    @SerializedName("public_repos")
    @ColumnInfo val public_repos: Int? = null,
    @SerializedName("public_gists")
    @ColumnInfo val public_gists: Int? = null,
    @SerializedName("followers")
    @ColumnInfo val followers: Int? = null,
    @SerializedName("following")
    @ColumnInfo val following: Int? = null,
    @SerializedName("created_at")
    @ColumnInfo val created_at: String? = null,
    @SerializedName("updated_at")
    @ColumnInfo val updated_at: String? = null,
    @ColumnInfo var notes: String? = null,
    @ColumnInfo var isInverted: Boolean? = false
) : Parcelable