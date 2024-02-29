package io.spherelabs.resource.icons

import androidx.compose.ui.graphics.vector.ImageVector
import io.spherelabs.resource.icons.socialmedia.*


interface SocialMediaResourceProvider {
    fun loadMedia(name: String): ImageVector
}

class DefaultSocialMediaResourceProvider : SocialMediaResourceProvider {

    private val resources = mapOf(
        "Behance" to SocialMediaIcons.Behance,
        "Badoo" to SocialMediaIcons.Badoo,
        "BloggerMedia" to SocialMediaIcons.BloggerMedia,
        "Couchsurfing" to SocialMediaIcons.Couchsurfing,
        "Default" to SocialMediaIcons.Default,
        "Delicious" to SocialMediaIcons.Delicious,
        "Discord" to SocialMediaIcons.Discord,
        "Dribbble" to SocialMediaIcons.Dribbble,
        "Drupal" to SocialMediaIcons.Drupal,
        "Ello" to SocialMediaIcons.Ello,
        "Facebook" to SocialMediaIcons.Facebook,
        "Flickr" to SocialMediaIcons.Flickr,
        "Foursquare" to SocialMediaIcons.Foursquare,
        "Goodreads" to SocialMediaIcons.Goodreads,
        "Houzz" to SocialMediaIcons.Houzz,
        "Imgur" to SocialMediaIcons.Imgur,
        "Instagram" to SocialMediaIcons.Instagram,
        "Linkedin" to SocialMediaIcons.Linkedin,
        "Mastodon" to SocialMediaIcons.Mastodon,
        "Medium" to SocialMediaIcons.Medium,
        "Meetup" to SocialMediaIcons.Meetup,
        "Mix" to SocialMediaIcons.Mix,
        "Mixcloud" to SocialMediaIcons.Mixcloud,
        "Myspace" to SocialMediaIcons.Myspace,
        "Neftlix" to SocialMediaIcons.Neftlix,
        "Nextdoor" to SocialMediaIcons.Nextdoor,
        "Messenger" to SocialMediaIcons.Messenger,
        "Pinterest" to SocialMediaIcons.Pinterest,
        "Quora" to SocialMediaIcons.Quora,
        "Reddit" to SocialMediaIcons.Reddit,
        "Reverbnation" to SocialMediaIcons.Reverbnation,
        "Skype" to SocialMediaIcons.Skype,
        "Slack" to SocialMediaIcons.Slack,
        "Snapchat" to SocialMediaIcons.Snapchat,
        "Soundcloud" to SocialMediaIcons.Soundcloud,
        "Spotify" to SocialMediaIcons.Spotify,
        "Steemit" to SocialMediaIcons.Steemit,
        "Trello" to SocialMediaIcons.Trello,
        "Twitch" to SocialMediaIcons.Twitch,
        "Telegram" to SocialMediaIcons.Telegram,
        "Taringa" to SocialMediaIcons.Taringa,
        "Vimeo" to SocialMediaIcons.Vimeo,
        "Wattpad" to SocialMediaIcons.Wattpad,
        "Wechat" to SocialMediaIcons.Wechat,
        "Vk" to SocialMediaIcons.Vk,
        "Whatsapp" to SocialMediaIcons.Whatsapp,
        "Yelp" to SocialMediaIcons.Yelp,
        "Youtube" to SocialMediaIcons.Youtube,
        "Zoom" to SocialMediaIcons.Zoom
    )

    override fun loadMedia(name: String): ImageVector {
        return resources.getOrElse(name) { SocialMediaIcons.Default }
    }
}
