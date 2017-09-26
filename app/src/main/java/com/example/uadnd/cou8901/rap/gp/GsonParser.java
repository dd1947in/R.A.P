package com.example.uadnd.cou8901.rap.gp;

import android.content.Context;
import android.util.Log;

import com.example.uadnd.cou8901.rap.db.Me;
import com.example.uadnd.cou8901.rap.db.Post;
import com.example.uadnd.cou8901.rap.db.Subreddit;
import com.google.gson.Gson;

import java.util.ArrayList;

import timber.log.Timber;

import static com.example.uadnd.cou8901.rap.db.SQLiteRedditObjects.persistPost;

/**
 * Created by dd2568 on 9/9/2017.
 */

public class GsonParser {

    /*
      Here are soome sample jsons and API ccalls
     */
    /*
    //https://oauth.reddit.com/api/v1/me
    static String apiV1Me = "{\n" +
            "\n" +
            "    \"is_employee\": false,\n" +
            "    \"features\": {\n" +
            "        \"search_public_traffic\": {\n" +
            "            \"owner\": \"search\",\n" +
            "            \"variant\": \"control_2\",\n" +
            "            \"experiment_id\": 212\n" +
            "        },\n" +
            "        \"do_not_track\": true,\n" +
            "        \"show_amp_link\": true,\n" +
            "        \"live_happening_now\": true,\n" +
            "        \"adserver_reporting\": true,\n" +
            "        \"give_hsts_grants\": true,\n" +
            "        \"legacy_search_pref\": true,\n" +
            "        \"listing_service_rampup\": true,\n" +
            "        \"mobile_web_targeting\": true,\n" +
            "        \"default_srs_holdout\": {\n" +
            "            \"owner\": \"relevance\",\n" +
            "            \"variant\": \"tutorial\",\n" +
            "            \"experiment_id\": 171\n" +
            "        },\n" +
            "        \"adzerk_do_not_track\": true,\n" +
            "        \"users_listing\": true,\n" +
            "        \"geopopular_in_holdout\": {\n" +
            "            \"owner\": \"relevance\",\n" +
            "            \"variant\": \"control_2\",\n" +
            "            \"experiment_id\": 210\n" +
            "        },\n" +
            "        \"whitelisted_pms\": true,\n" +
            "        \"inbox_push\": true,\n" +
            "        \"personalization_prefs\": true,\n" +
            "        \"upgrade_cookies\": true,\n" +
            "        \"interest_targeting\": true,\n" +
            "        \"new_report_flow\": true,\n" +
            "        \"block_user_by_report\": true,\n" +
            "        \"ads_auto_refund\": true,\n" +
            "        \"orangereds_as_emails\": true,\n" +
            "        \"mweb_xpromo_modal_listing_click_daily_dismissible_ios\": true,\n" +
            "        \"heartbeat_events\": true,\n" +
            "        \"expando_events\": true,\n" +
            "        \"eu_cookie_policy\": true,\n" +
            "        \"utm_comment_links\": true,\n" +
            "        \"force_https\": true,\n" +
            "        \"activity_service_write\": true,\n" +
            "        \"post_to_profile_beta\": true,\n" +
            "        \"reddituploads_redirect\": true,\n" +
            "        \"outbound_clicktracking\": true,\n" +
            "        \"show_user_sr_name\": true,\n" +
            "        \"new_loggedin_cache_policy\": true,\n" +
            "        \"https_redirect\": true,\n" +
            "        \"geopopular_us_holdout\": {\n" +
            "            \"owner\": \"relevance\",\n" +
            "            \"variant\": \"geopopular_holdout\",\n" +
            "            \"experiment_id\": 215\n" +
            "        },\n" +
            "        \"search_dark_traffic\": true,\n" +
            "        \"mweb_xpromo_interstitial_comments_ios\": true,\n" +
            "        \"pause_ads\": true,\n" +
            "        \"programmatic_ads\": true,\n" +
            "        \"geopopular\": true,\n" +
            "        \"show_recommended_link\": true,\n" +
            "        \"mweb_xpromo_interstitial_comments_android\": true,\n" +
            "        \"ads_auction\": true,\n" +
            "        \"screenview_events\": true,\n" +
            "        \"new_report_dialog\": true,\n" +
            "        \"moat_tracking\": true,\n" +
            "        \"subreddit_rules\": true,\n" +
            "        \"geopopular_mx_holdout\": {\n" +
            "            \"owner\": \"relevance\",\n" +
            "            \"variant\": \"control_2\",\n" +
            "            \"experiment_id\": 202\n" +
            "        },\n" +
            "        \"mobile_settings\": true,\n" +
            "        \"adzerk_reporting_2\": true,\n" +
            "        \"mobile_native_banner\": true,\n" +
            "        \"ads_auto_extend\": true,\n" +
            "        \"crossposting_users\": true,\n" +
            "        \"post_embed\": true,\n" +
            "        \"scroll_events\": true,\n" +
            "        \"mweb_xpromo_modal_listing_click_daily_dismissible_android\": true,\n" +
            "        \"adblock_test\": true,\n" +
            "        \"activity_service_read\": true\n" +
            "    },\n" +
            "    \"pref_no_profanity\": true,\n" +
            "    \"is_suspended\": false,\n" +
            "    \"pref_geopopular\": null,\n" +
            "    \"subreddit\": null,\n" +
            "    \"is_sponsor\": false,\n" +
            "    \"gold_expiration\": null,\n" +
            "    \"id\": \"a9n9eek\",\n" +
            "    \"suspension_expiration_utc\": null,\n" +
            "    \"verified\": false,\n" +
            "    \"new_modmail_exists\": null,\n" +
            "    \"over_18\": false,\n" +
            "    \"is_gold\": false,\n" +
            "    \"is_mod\": false,\n" +
            "    \"has_verified_email\": true,\n" +
            "    \"has_mod_mail\": false,\n" +
            "    \"oauth_client_id\": \"Hdryf5E-LdFS8Q\",\n" +
            "    \"hide_from_robots\": true,\n" +
            "    \"link_karma\": 1,\n" +
            "    \"inbox_count\": 0,\n" +
            "    \"pref_top_karma_subreddits\": null,\n" +
            "    \"has_mail\": false,\n" +
            "    \"pref_show_snoovatar\": false,\n" +
            "    \"name\": \"cou8901\",\n" +
            "    \"created\": 1502584160.0,\n" +
            "    \"gold_creddits\": 0,\n" +
            "    \"created_utc\": 1502555360.0,\n" +
            "    \"in_beta\": false,\n" +
            "    \"comment_karma\": 0,\n" +
            "    \"has_subscribed\": true\n" +
            "\n" +
            "}" ;
    //https://www.reddit.com/subreddits/mine/subscriber.json
    static String subredditsMineSubscriberJson = "{\n" +
            "\n" +
            "    \"kind\": \"Listing\",\n" +
            "    \"data\": {\n" +
            "        \"modhash\": \"1h8v2up0fn346efa148a0c01550ba716c4000c9498c59149a8\",\n" +
            "        \"children\": [\n" +
            "            {\n" +
            "                \"kind\": \"t5\",\n" +
            "                \"data\": {\n" +
            "                    \"user_is_contributor\": false,\n" +
            "                    \"banner_img\": \"\",\n" +
            "                    \"user_flair_text\": null,\n" +
            "                    \"submit_text_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;&lt;strong&gt;If You Are A Comic Artist&lt;/strong&gt;    &lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;Comic artists are welcome to post their own work any way they want.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;If you don&amp;#39;t want to link to your website, put [OC] in your post title (it means &amp;quot;Original Content&amp;quot;) or &lt;a href=\\\"/message/compose?to=%2Fr%2Fcomics\\\"&gt;request Artist Flair&lt;/a&gt;.&lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;p&gt;&lt;strong&gt;If You Are Not An Artist&lt;/strong&gt;  &lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;Link to an original source like the artist&amp;#39;s website or a syndication site.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;Link to the page, not the image file, so the artist gets the credit and site traffic.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;Imgur/rehost links are ok for translations, but you must also state clearly it is a translation and link to the non-English source in a comment.&lt;br/&gt;&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"user_is_banned\": false,\n" +
            "                    \"wiki_enabled\": true,\n" +
            "                    \"show_media\": true,\n" +
            "                    \"id\": \"2qh0s\",\n" +
            "                    \"description\": \"##About \\\\/r/Comics\\n\\n&gt; This subreddit is for everything related to print comics and web comics.  Artists are encouraged to post their own work.  News and media for adaptations based on comic books are welcome. Read [the subreddit wiki](https://www.reddit.com/r/comics/wiki/index) for more information about the subreddit.\\n\\n\\n##[General Conduct](/r/Comics/wiki/index#wiki_general_conduct)\\n\\n&gt; 1. **[Don't complain about comics you don't like or understand.](/r/Comics/wiki/index#wiki_1._don.27t_complain_about_comics_you_don.27t_like.)** - If you don't get a comic, it's probably not meant for you.  Don't whine about it; just move on to the next comic.\\n2. **[Don't attack artists posting their content.](/r/Comics/wiki/index#wiki_2._don.27t_attack_artists_posting_their_content)** - It's not ok to attack artists with hateful comments meant to tear them down or try to chase them off Reddit.\\n3. **[Don't make drama for drama's sake. No trolling.](/r/Comics/wiki/index#wiki_3._don.27t_make_drama_for_drama.27s_sake._no_trolling.)** - Anyone wanting to participate in this community needs to keep their comments civil.\\n4. **[Respect the [OC] System](/r/Comics/wiki/index#wiki_4._respect_the_.5Boc.5D_system)** - The [OC] tag is for artists wanting to submit their Original Content.  False claims on others' art will get you banned.\\n5. **[Respect the Report System](/r/Comics/wiki/index#wiki_5._respect_the_report_system)** - Reports are for content that breaks this sub's/Reddit's rules.  False reports are given to the Reddit Admins. \\n6. **[Tag NSFW content when needed.](/r/Comics/wiki/index#wiki_6._tag_nsfw_content_when_needed.)** - Content with nudity, pornography, or obvious profanity should be tagged as NSFW.  \\n\\n##[What is OK to Post](/r/Comics/wiki/index#wiki_what_is_ok_to_post)\\n\\n&gt; * **Comic covers or short excerpts** - *A few* scanned pages can be posted for discussion.  Don't post entire comic books.  Posts with too many pages will get removed.\\n* **Web comic strips** - Most strips need to be linked back to original sources (i.e. the artists' websites).   Don't link to [rehosted](/r/Comics/wiki/index#wiki_what_is_rehosting) or [hotlinked](/r/Comics/wiki/index#wiki_what_is_hotlinking) comics unless they're in the [exception whitelist](/r/Comics/wiki/index#wiki_established_comic_whitelist).  Artists can use [OC] to link to their own work anywhere.\\n* **Links to articles, videos, art, cosplay or music** - Content directly related to comic books or web comics, including film and TV media.  \\n* **Discussion topics** - Talk about past or present comic books or strips.  Topics about comic-based movies or TV shows are also fine.\\n* **Translations** - English translations of non-English original comics are ok as long as the translation is honest and a link to the original source is provided in a comment.\\n\\n##[What is not OK to Post](/r/Comics/wiki/index#wiki_what_is_not_ok_to_post)\\n\\n&gt; * **Edits to comics that you didn't make** - Don't remove watermarks/attribution or try to \\\"fix\\\" someone else's work.  Post your own original content.\\n* **Stuff that just reminds you of comics without there being any real reference in the topic** - Please keep posts on topic with real comic content.\\n* **Don't put the punchline in the post title** - Links to humor comics that are submitted with joke-ruining titles may be removed.  \\n* **Links to download complete, pirated comic books** - Please support comics by buying them.\\n* **Shitposts** --  Low-effort stick-figure/MS Paint drawing/meme or other lazy/meta/circlejerk content is not welcome. Don't post rage comics, non-comic memes, or CAPTCHAs\\n\\n##[Are You A Comic Artist?](/r/Comics/wiki/index#wiki_are_you_a_comic_artist.3F)\\n\\n&gt; Content creators can get Artist Flair to display next to their account names on all of their posts. [Message the moderators](/message/compose?to=%2Fr%2FComics) with some proof and the name of your strip for some blue flair.  Artists without flair can add [OC] to post titles to identify their \\\"Original Content.\\\" Tagging a post with [OC] allows links to general hosting sites like Imgur.\\n\\n&gt; &amp;nbsp;\\n    \\n&gt; [ ](http://www.reddit.com/message/compose?to=%23comics \\\"flair-talk\\\")\\n\\n##Related Subreddits\\n\\n&gt; * /r/comicbooks\\n* /r/webcomics\\n* /r/manga\\n* /r/graphicnovels\\n* /r/comicbookart\\n* /r/comiccon\\n* /r/comic_crits\\n* /r/wholesomecomics \\n* [Marvel Subreddits](/r/comics/wiki/marvel)\\n* [DC Subreddits](/r/comics/wiki/dc)\\n* [Image Subreddits](/r/comics/wiki/image)\\n* [Vertigo Subreddits](/r/comics/wiki/vertigo)\\n* [Dark Horse Subreddits](/r/comics/wiki/darkhorse)\\n* [Valiant Subreddits](/r/comics/wiki/valiant)\\n* [Manga Subreddits](/r/comics/wiki/manga)\\n* [Other Comics-Related Subreddits](/r/comics/wiki/other)\\n\\n\\n\",\n" +
            "                    \"submit_text\": \"**If You Are A Comic Artist**    \\n\\n* Comic artists are welcome to post their own work any way they want.   \\n* If you don't want to link to your website, put [OC] in your post title (it means \\\"Original Content\\\") or [request Artist Flair](/message/compose?to=%2Fr%2Fcomics).\\n\\n**If You Are Not An Artist**  \\n  \\n* Link to an original source like the artist's website or a syndication site.  \\n* Link to the page, not the image file, so the artist gets the credit and site traffic.         \\n* Imgur/rehost links are ok for translations, but you must also state clearly it is a translation and link to the non-English source in a comment.  \",\n" +
            "                    \"user_can_flair_in_sr\": null,\n" +
            "                    \"display_name\": \"comics\",\n" +
            "                    \"header_img\": \"https://c.thumbs.redditmedia.com/uIbyX8MSVa0J6mKV.png\",\n" +
            "                    \"description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;h2&gt;About /r/Comics&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;p&gt;This subreddit is for everything related to print comics and web comics.  Artists are encouraged to post their own work.  News and media for adaptations based on comic books are welcome. Read &lt;a href=\\\"https://www.reddit.com/r/comics/wiki/index\\\"&gt;the subreddit wiki&lt;/a&gt; for more information about the subreddit.&lt;/p&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_general_conduct\\\"&gt;General Conduct&lt;/a&gt;&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;ol&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_1._don.27t_complain_about_comics_you_don.27t_like.\\\"&gt;Don&amp;#39;t complain about comics you don&amp;#39;t like or understand.&lt;/a&gt;&lt;/strong&gt; - If you don&amp;#39;t get a comic, it&amp;#39;s probably not meant for you.  Don&amp;#39;t whine about it; just move on to the next comic.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_2._don.27t_attack_artists_posting_their_content\\\"&gt;Don&amp;#39;t attack artists posting their content.&lt;/a&gt;&lt;/strong&gt; - It&amp;#39;s not ok to attack artists with hateful comments meant to tear them down or try to chase them off Reddit.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_3._don.27t_make_drama_for_drama.27s_sake._no_trolling.\\\"&gt;Don&amp;#39;t make drama for drama&amp;#39;s sake. No trolling.&lt;/a&gt;&lt;/strong&gt; - Anyone wanting to participate in this community needs to keep their comments civil.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_4._respect_the_.5Boc.5D_system\\\"&gt;Respect the [OC] System&lt;/a&gt;&lt;/strong&gt; - The [OC] tag is for artists wanting to submit their Original Content.  False claims on others&amp;#39; art will get you banned.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_5._respect_the_report_system\\\"&gt;Respect the Report System&lt;/a&gt;&lt;/strong&gt; - Reports are for content that breaks this sub&amp;#39;s/Reddit&amp;#39;s rules.  False reports are given to the Reddit Admins. &lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_6._tag_nsfw_content_when_needed.\\\"&gt;Tag NSFW content when needed.&lt;/a&gt;&lt;/strong&gt; - Content with nudity, pornography, or obvious profanity should be tagged as NSFW.&lt;br/&gt;&lt;/li&gt;\\n&lt;/ol&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_what_is_ok_to_post\\\"&gt;What is OK to Post&lt;/a&gt;&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;ul&gt;\\n&lt;li&gt;&lt;strong&gt;Comic covers or short excerpts&lt;/strong&gt; - &lt;em&gt;A few&lt;/em&gt; scanned pages can be posted for discussion.  Don&amp;#39;t post entire comic books.  Posts with too many pages will get removed.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Web comic strips&lt;/strong&gt; - Most strips need to be linked back to original sources (i.e. the artists&amp;#39; websites).   Don&amp;#39;t link to &lt;a href=\\\"/r/Comics/wiki/index#wiki_what_is_rehosting\\\"&gt;rehosted&lt;/a&gt; or &lt;a href=\\\"/r/Comics/wiki/index#wiki_what_is_hotlinking\\\"&gt;hotlinked&lt;/a&gt; comics unless they&amp;#39;re in the &lt;a href=\\\"/r/Comics/wiki/index#wiki_established_comic_whitelist\\\"&gt;exception whitelist&lt;/a&gt;.  Artists can use [OC] to link to their own work anywhere.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Links to articles, videos, art, cosplay or music&lt;/strong&gt; - Content directly related to comic books or web comics, including film and TV media.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Discussion topics&lt;/strong&gt; - Talk about past or present comic books or strips.  Topics about comic-based movies or TV shows are also fine.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Translations&lt;/strong&gt; - English translations of non-English original comics are ok as long as the translation is honest and a link to the original source is provided in a comment.&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_what_is_not_ok_to_post\\\"&gt;What is not OK to Post&lt;/a&gt;&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;ul&gt;\\n&lt;li&gt;&lt;strong&gt;Edits to comics that you didn&amp;#39;t make&lt;/strong&gt; - Don&amp;#39;t remove watermarks/attribution or try to &amp;quot;fix&amp;quot; someone else&amp;#39;s work.  Post your own original content.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Stuff that just reminds you of comics without there being any real reference in the topic&lt;/strong&gt; - Please keep posts on topic with real comic content.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Don&amp;#39;t put the punchline in the post title&lt;/strong&gt; - Links to humor comics that are submitted with joke-ruining titles may be removed.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Links to download complete, pirated comic books&lt;/strong&gt; - Please support comics by buying them.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Shitposts&lt;/strong&gt; --  Low-effort stick-figure/MS Paint drawing/meme or other lazy/meta/circlejerk content is not welcome. Don&amp;#39;t post rage comics, non-comic memes, or CAPTCHAs&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_are_you_a_comic_artist.3F\\\"&gt;Are You A Comic Artist?&lt;/a&gt;&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;p&gt;Content creators can get Artist Flair to display next to their account names on all of their posts. &lt;a href=\\\"/message/compose?to=%2Fr%2FComics\\\"&gt;Message the moderators&lt;/a&gt; with some proof and the name of your strip for some blue flair.  Artists without flair can add [OC] to post titles to identify their &amp;quot;Original Content.&amp;quot; Tagging a post with [OC] allows links to general hosting sites like Imgur.&lt;/p&gt;\\n\\n&lt;p&gt;&amp;nbsp;&lt;/p&gt;\\n\\n&lt;p&gt;&lt;a href=\\\"http://www.reddit.com/message/compose?to=%23comics\\\" title=\\\"flair-talk\\\"&gt; &lt;/a&gt;&lt;/p&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;Related Subreddits&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;ul&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comicbooks\\\"&gt;/r/comicbooks&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/webcomics\\\"&gt;/r/webcomics&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/manga\\\"&gt;/r/manga&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/graphicnovels\\\"&gt;/r/graphicnovels&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comicbookart\\\"&gt;/r/comicbookart&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comiccon\\\"&gt;/r/comiccon&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comic_crits\\\"&gt;/r/comic_crits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/wholesomecomics\\\"&gt;/r/wholesomecomics&lt;/a&gt; &lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/marvel\\\"&gt;Marvel Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/dc\\\"&gt;DC Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/image\\\"&gt;Image Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/vertigo\\\"&gt;Vertigo Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/darkhorse\\\"&gt;Dark Horse Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/valiant\\\"&gt;Valiant Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/manga\\\"&gt;Manga Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/other\\\"&gt;Other Comics-Related Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/blockquote&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"title\": \"Print Comics and Webcomics \",\n" +
            "                    \"collapse_deleted_comments\": true,\n" +
            "                    \"user_has_favorited\": false,\n" +
            "                    \"public_description\": \"Everything related to print comics (comic books, graphic novels, and strips) and web comics.  Artists are encouraged to post their own work.  News and media for adaptations based on comic books are welcome. Read [the subreddit wiki](https://www.reddit.com/r/comics/wiki/index) for more information about the subreddit.\",\n" +
            "                    \"over18\": false,\n" +
            "                    \"public_description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;Everything related to print comics (comic books, graphic novels, and strips) and web comics.  Artists are encouraged to post their own work.  News and media for adaptations based on comic books are welcome. Read &lt;a href=\\\"https://www.reddit.com/r/comics/wiki/index\\\"&gt;the subreddit wiki&lt;/a&gt; for more information about the subreddit.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"allow_videos\": true,\n" +
            "                    \"spoilers_enabled\": true,\n" +
            "                    \"icon_size\": null,\n" +
            "                    \"audience_target\": \"comics_hobbies,humor\",\n" +
            "                    \"suggested_comment_sort\": null,\n" +
            "                    \"active_user_count\": null,\n" +
            "                    \"icon_img\": \"\",\n" +
            "                    \"header_title\": \"300,000 subscribers! Thanks to JimKB for the logo!\",\n" +
            "                    \"display_name_prefixed\": \"r/comics\",\n" +
            "                    \"user_is_muted\": false,\n" +
            "                    \"submit_link_label\": null,\n" +
            "                    \"accounts_active\": null,\n" +
            "                    \"public_traffic\": false,\n" +
            "                    \"header_size\": [\n" +
            "                        175,\n" +
            "                        40\n" +
            "                    ],\n" +
            "                    \"subscribers\": 723382,\n" +
            "                    \"user_flair_css_class\": null,\n" +
            "                    \"submit_text_label\": null,\n" +
            "                    \"key_color\": \"#0079d3\",\n" +
            "                    \"user_sr_flair_enabled\": null,\n" +
            "                    \"lang\": \"en\",\n" +
            "                    \"is_enrolled_in_new_modmail\": null,\n" +
            "                    \"whitelist_status\": \"all_ads\",\n" +
            "                    \"name\": \"t5_2qh0s\",\n" +
            "                    \"user_flair_enabled_in_sr\": false,\n" +
            "                    \"created\": 1201248652.0,\n" +
            "                    \"url\": \"/r/comics/\",\n" +
            "                    \"quarantine\": false,\n" +
            "                    \"hide_ads\": false,\n" +
            "                    \"created_utc\": 1201219852.0,\n" +
            "                    \"banner_size\": null,\n" +
            "                    \"user_is_moderator\": false,\n" +
            "                    \"accounts_active_is_fuzzed\": false,\n" +
            "                    \"advertiser_category\": \"Entertainment\",\n" +
            "                    \"user_sr_theme_enabled\": true,\n" +
            "                    \"link_flair_enabled\": true,\n" +
            "                    \"allow_images\": true,\n" +
            "                    \"show_media_preview\": true,\n" +
            "                    \"comment_score_hide_mins\": 0,\n" +
            "                    \"subreddit_type\": \"public\",\n" +
            "                    \"submission_type\": \"any\",\n" +
            "                    \"user_is_subscriber\": true\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"kind\": \"t5\",\n" +
            "                \"data\": {\n" +
            "                    \"user_is_contributor\": false,\n" +
            "                    \"banner_img\": \"\",\n" +
            "                    \"user_flair_text\": null,\n" +
            "                    \"submit_text_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;Please read &lt;a href=\\\"/r/pics/about/sidebar\\\"&gt;the sidebar&lt;/a&gt; before submitting&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"user_is_banned\": false,\n" +
            "                    \"wiki_enabled\": true,\n" +
            "                    \"show_media\": true,\n" +
            "                    \"id\": \"2qh0u\",\n" +
            "                    \"description\": \"A place to share photographs and pictures. Feel free to post your own, but please **read the rules first** (see below), and note that we are *not a catch-all* for general images (of screenshots, comics, etc.)\\n\\n---\\n\\n#Spoiler code#\\n\\nPlease mark spoilers like this:  \\n`[text here](/spoiler)`\\n\\nHover over to [read](/spoiler).\\n\\n---\\nCheck out http://nt.reddit.com/r/pics!\\n\\nCheck out /r/pics/w/v2/resources/takedown for help with taking down posts due to copyright or personal identifiable information reasons. \\n\\n---\\n#[Posting Rules](/r/pics/about/rules)#\\n\\n1. **No screenshots or pictures of screens.  No pictures with added/superimposed text.** *This includes [image macros](http://en.wikipedia.org/wiki/Image_macro), comics, maps, infographics, and most diagrams. Text (e.g. a URL) serving to credit the original author is exempt.*\\n\\n1. **No porn or gore.** *NSFW content must be tagged.*\\n\\n1. **No personal information.** *This includes anything hosted on Facebook's servers, as they can be traced to the original account holder. Stalking &amp; harassment will not be tolerated.* ***No missing-persons requests!***\\n\\n1. **Titles must follow all [title guidelines](/r/pics/w/titles).**\\n\\n1. **Submissions must link directly to a specific image file or to a website with minimal ads.** *We do not allow blog hosting of images (\\\"blogspam\\\"), but links to albums on image hosting websites are okay. URL shorteners are prohibited. URLs in image or album descriptions are prohibited.* \\n\\n1. **No animated images.** *Please submit them to /r/gif, /r/gifs, or /r/reactiongifs instead.*\\n\\n1. We enforce a standard of common decency and civility here. **Please be respectful to others.** Personal attacks, bigotry, fighting words, otherwise inappropriate behavior or content, comments that insult or demean a specific user or group of users will be removed. Regular or egregious violations will result in a ban.\\n\\n* If your submission appears to be filtered, but **definitely** meets the above rules, [please send us a message](/message/compose?to=%23pics) with a link to the **comments section** of your post (not a direct link to the image). **Don't delete it**  as that just makes the filter hate you! \\n\\n* If you come across any rule violations please report the submission or  [message the mods](http://www.reddit.com/message/compose?to=%23pics) and one of us will remove it!\\n\\n* Serial reposters may be filtered. False claims of ownership will result in a ban.\\n\\n* **Professional photographer or artist?** Read [these guidelines](/r/pics/wiki/professionals) for linking to your own site and obtaining 'Verified' user flair. \\n\\n#Links#\\nIf your post doesn't meet the above rules, consider submitting it on one of these other subreddits:\\n\\n#Subreddits\\nBelow is a table of subreddits that you might want to check out!\\n\\nScreenshots | Advice Animals\\n-----------|--------------\\n/r/images | /r/adviceanimals\\n/r/screenshots | /r/memes\\n/r/desktops | /r/memesIRL\\n/r/amoledbackgrounds | /r/wholesomememes \\n**Animals** | **More Animals**\\n/r/aww | /r/rabbits\\n/r/dogs | /r/BeforeNAfterAdoption\\n/r/cats | /r/otters\\n/r/foxes | /r/redpandas\\n**GIFS** | **HQ / Curated**\\n/r/gifs | /r/pic\\n/r/catgifs | /r/earthporn\\n/r/reactiongifs | /r/spaceporn\\n\\n##Topic subreddits\\n\\nEvery now and then, we choose 2 new topics, and find some subreddits about that topic to feature!\\n\\nOne Word | Art\\n-----|----------\\n/r/catsstandingup | /r/Art\\n/r/nocontextpics | /r/ImaginaryBestOf\\n&amp;nbsp; | /r/IDAP \",\n" +
            "                    \"submit_text\": \"Please read [the sidebar](/r/pics/about/sidebar) before submitting\\n\\n\",\n" +
            "                    \"user_can_flair_in_sr\": null,\n" +
            "                    \"display_name\": \"pics\",\n" +
            "                    \"header_img\": \"https://b.thumbs.redditmedia.com/MfVUZn6wkAaWadas1QAInsX3excC1gp4X7Y3jm_FBiQ.png\",\n" +
            "                    \"description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;A place to share photographs and pictures. Feel free to post your own, but please &lt;strong&gt;read the rules first&lt;/strong&gt; (see below), and note that we are &lt;em&gt;not a catch-all&lt;/em&gt; for general images (of screenshots, comics, etc.)&lt;/p&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;h1&gt;Spoiler code&lt;/h1&gt;\\n\\n&lt;p&gt;Please mark spoilers like this:&lt;br/&gt;\\n&lt;code&gt;[text here](/spoiler)&lt;/code&gt;&lt;/p&gt;\\n\\n&lt;p&gt;Hover over to &lt;a href=\\\"/spoiler\\\"&gt;read&lt;/a&gt;.&lt;/p&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;p&gt;Check out &lt;a href=\\\"http://nt.reddit.com/r/pics\\\"&gt;http://nt.reddit.com/r/pics&lt;/a&gt;!&lt;/p&gt;\\n\\n&lt;p&gt;Check out &lt;a href=\\\"/r/pics/w/v2/resources/takedown\\\"&gt;/r/pics/w/v2/resources/takedown&lt;/a&gt; for help with taking down posts due to copyright or personal identifiable information reasons. &lt;/p&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;h1&gt;&lt;a href=\\\"/r/pics/about/rules\\\"&gt;Posting Rules&lt;/a&gt;&lt;/h1&gt;\\n\\n&lt;ol&gt;\\n&lt;li&gt;&lt;p&gt;&lt;strong&gt;No screenshots or pictures of screens.  No pictures with added/superimposed text.&lt;/strong&gt; &lt;em&gt;This includes &lt;a href=\\\"http://en.wikipedia.org/wiki/Image_macro\\\"&gt;image macros&lt;/a&gt;, comics, maps, infographics, and most diagrams. Text (e.g. a URL) serving to credit the original author is exempt.&lt;/em&gt;&lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;&lt;strong&gt;No porn or gore.&lt;/strong&gt; &lt;em&gt;NSFW content must be tagged.&lt;/em&gt;&lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;&lt;strong&gt;No personal information.&lt;/strong&gt; &lt;em&gt;This includes anything hosted on Facebook&amp;#39;s servers, as they can be traced to the original account holder. Stalking &amp;amp; harassment will not be tolerated.&lt;/em&gt; &lt;strong&gt;&lt;em&gt;No missing-persons requests!&lt;/em&gt;&lt;/strong&gt;&lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;&lt;strong&gt;Titles must follow all &lt;a href=\\\"/r/pics/w/titles\\\"&gt;title guidelines&lt;/a&gt;.&lt;/strong&gt;&lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;&lt;strong&gt;Submissions must link directly to a specific image file or to a website with minimal ads.&lt;/strong&gt; &lt;em&gt;We do not allow blog hosting of images (&amp;quot;blogspam&amp;quot;), but links to albums on image hosting websites are okay. URL shorteners are prohibited. URLs in image or album descriptions are prohibited.&lt;/em&gt; &lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;&lt;strong&gt;No animated images.&lt;/strong&gt; &lt;em&gt;Please submit them to &lt;a href=\\\"/r/gif\\\"&gt;/r/gif&lt;/a&gt;, &lt;a href=\\\"/r/gifs\\\"&gt;/r/gifs&lt;/a&gt;, or &lt;a href=\\\"/r/reactiongifs\\\"&gt;/r/reactiongifs&lt;/a&gt; instead.&lt;/em&gt;&lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;We enforce a standard of common decency and civility here. &lt;strong&gt;Please be respectful to others.&lt;/strong&gt; Personal attacks, bigotry, fighting words, otherwise inappropriate behavior or content, comments that insult or demean a specific user or group of users will be removed. Regular or egregious violations will result in a ban.&lt;/p&gt;&lt;/li&gt;\\n&lt;/ol&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;&lt;p&gt;If your submission appears to be filtered, but &lt;strong&gt;definitely&lt;/strong&gt; meets the above rules, &lt;a href=\\\"/message/compose?to=%23pics\\\"&gt;please send us a message&lt;/a&gt; with a link to the &lt;strong&gt;comments section&lt;/strong&gt; of your post (not a direct link to the image). &lt;strong&gt;Don&amp;#39;t delete it&lt;/strong&gt;  as that just makes the filter hate you! &lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;If you come across any rule violations please report the submission or  &lt;a href=\\\"http://www.reddit.com/message/compose?to=%23pics\\\"&gt;message the mods&lt;/a&gt; and one of us will remove it!&lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;Serial reposters may be filtered. False claims of ownership will result in a ban.&lt;/p&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;p&gt;&lt;strong&gt;Professional photographer or artist?&lt;/strong&gt; Read &lt;a href=\\\"/r/pics/wiki/professionals\\\"&gt;these guidelines&lt;/a&gt; for linking to your own site and obtaining &amp;#39;Verified&amp;#39; user flair. &lt;/p&gt;&lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;h1&gt;Links&lt;/h1&gt;\\n\\n&lt;p&gt;If your post doesn&amp;#39;t meet the above rules, consider submitting it on one of these other subreddits:&lt;/p&gt;\\n\\n&lt;h1&gt;Subreddits&lt;/h1&gt;\\n\\n&lt;p&gt;Below is a table of subreddits that you might want to check out!&lt;/p&gt;\\n\\n&lt;table&gt;&lt;thead&gt;\\n&lt;tr&gt;\\n&lt;th&gt;Screenshots&lt;/th&gt;\\n&lt;th&gt;Advice Animals&lt;/th&gt;\\n&lt;/tr&gt;\\n&lt;/thead&gt;&lt;tbody&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/images\\\"&gt;/r/images&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/adviceanimals\\\"&gt;/r/adviceanimals&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/screenshots\\\"&gt;/r/screenshots&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/memes\\\"&gt;/r/memes&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/desktops\\\"&gt;/r/desktops&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/memesIRL\\\"&gt;/r/memesIRL&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/amoledbackgrounds\\\"&gt;/r/amoledbackgrounds&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/wholesomememes\\\"&gt;/r/wholesomememes&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;strong&gt;Animals&lt;/strong&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;strong&gt;More Animals&lt;/strong&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/aww\\\"&gt;/r/aww&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/rabbits\\\"&gt;/r/rabbits&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/dogs\\\"&gt;/r/dogs&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/BeforeNAfterAdoption\\\"&gt;/r/BeforeNAfterAdoption&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/cats\\\"&gt;/r/cats&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/otters\\\"&gt;/r/otters&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/foxes\\\"&gt;/r/foxes&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/redpandas\\\"&gt;/r/redpandas&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;strong&gt;GIFS&lt;/strong&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;strong&gt;HQ / Curated&lt;/strong&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/gifs\\\"&gt;/r/gifs&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/pic\\\"&gt;/r/pic&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/catgifs\\\"&gt;/r/catgifs&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/earthporn\\\"&gt;/r/earthporn&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/reactiongifs\\\"&gt;/r/reactiongifs&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/spaceporn\\\"&gt;/r/spaceporn&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;/tbody&gt;&lt;/table&gt;\\n\\n&lt;h2&gt;Topic subreddits&lt;/h2&gt;\\n\\n&lt;p&gt;Every now and then, we choose 2 new topics, and find some subreddits about that topic to feature!&lt;/p&gt;\\n\\n&lt;table&gt;&lt;thead&gt;\\n&lt;tr&gt;\\n&lt;th&gt;One Word&lt;/th&gt;\\n&lt;th&gt;Art&lt;/th&gt;\\n&lt;/tr&gt;\\n&lt;/thead&gt;&lt;tbody&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/catsstandingup\\\"&gt;/r/catsstandingup&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/Art\\\"&gt;/r/Art&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/nocontextpics\\\"&gt;/r/nocontextpics&lt;/a&gt;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/ImaginaryBestOf\\\"&gt;/r/ImaginaryBestOf&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;tr&gt;\\n&lt;td&gt;&amp;nbsp;&lt;/td&gt;\\n&lt;td&gt;&lt;a href=\\\"/r/IDAP\\\"&gt;/r/IDAP&lt;/a&gt;&lt;/td&gt;\\n&lt;/tr&gt;\\n&lt;/tbody&gt;&lt;/table&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"title\": \"Reddit Pics\",\n" +
            "                    \"collapse_deleted_comments\": true,\n" +
            "                    \"user_has_favorited\": false,\n" +
            "                    \"public_description\": \"A place to share photographs and pictures.\",\n" +
            "                    \"over18\": false,\n" +
            "                    \"public_description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;A place to share photographs and pictures.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"spoilers_enabled\": true,\n" +
            "                    \"icon_size\": [\n" +
            "                        256,\n" +
            "                        256\n" +
            "                    ],\n" +
            "                    \"audience_target\": \"pictures\",\n" +
            "                    \"suggested_comment_sort\": null,\n" +
            "                    \"active_user_count\": null,\n" +
            "                    \"icon_img\": \"https://b.thumbs.redditmedia.com/VZX_KQLnI1DPhlEZ07bIcLzwR1Win808RIt7zm49VIQ.png\",\n" +
            "                    \"header_title\": \"™\",\n" +
            "                    \"display_name_prefixed\": \"r/pics\",\n" +
            "                    \"user_is_muted\": false,\n" +
            "                    \"submit_link_label\": \"Submit an image\",\n" +
            "                    \"accounts_active\": null,\n" +
            "                    \"public_traffic\": false,\n" +
            "                    \"header_size\": [\n" +
            "                        160,\n" +
            "                        64\n" +
            "                    ],\n" +
            "                    \"subscribers\": 17503877,\n" +
            "                    \"user_flair_css_class\": null,\n" +
            "                    \"submit_text_label\": null,\n" +
            "                    \"key_color\": \"#222222\",\n" +
            "                    \"user_sr_flair_enabled\": null,\n" +
            "                    \"lang\": \"en\",\n" +
            "                    \"is_enrolled_in_new_modmail\": null,\n" +
            "                    \"whitelist_status\": \"all_ads\",\n" +
            "                    \"name\": \"t5_2qh0u\",\n" +
            "                    \"user_flair_enabled_in_sr\": false,\n" +
            "                    \"created\": 1201249869.0,\n" +
            "                    \"url\": \"/r/pics/\",\n" +
            "                    \"quarantine\": false,\n" +
            "                    \"hide_ads\": false,\n" +
            "                    \"created_utc\": 1201221069.0,\n" +
            "                    \"banner_size\": null,\n" +
            "                    \"user_is_moderator\": false,\n" +
            "                    \"accounts_active_is_fuzzed\": false,\n" +
            "                    \"advertiser_category\": \"Lifestyles\",\n" +
            "                    \"user_sr_theme_enabled\": true,\n" +
            "                    \"link_flair_enabled\": true,\n" +
            "                    \"allow_images\": true,\n" +
            "                    \"show_media_preview\": true,\n" +
            "                    \"comment_score_hide_mins\": 60,\n" +
            "                    \"subreddit_type\": \"public\",\n" +
            "                    \"submission_type\": \"link\",\n" +
            "                    \"user_is_subscriber\": true\n" +
            "                }\n" +
            "            }\n" +
            "        ],\n" +
            "        \"after\": null,\n" +
            "        \"before\": null\n" +
            "    }\n" +
            "\n" +
            "}" ;

    //https://www.reddit.com/r/redditisfun.json
    static String rredditisfunjson = "{\n" +
            "\n" +
            "    \"kind\": \"Listing\",\n" +
            "    \"data\": {\n" +
            "        \"modhash\": \"5hqf464owqe9843329ebc38ea6ac183141b1a7b458e662f8b7\",\n" +
            "        \"children\": [\n" +
            "            {\n" +
            "                \"kind\": \"t3\",\n" +
            "                \"data\": {\n" +
            "                    \"domain\": \"self.redditisfun\",\n" +
            "                    \"approved_at_utc\": null,\n" +
            "                    \"banned_by\": null,\n" +
            "                    \"media_embed\": { },\n" +
            "                    \"thumbnail_width\": null,\n" +
            "                    \"subreddit\": \"redditisfun\",\n" +
            "                    \"selftext_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;blockquote&gt;\\n&lt;p&gt;&lt;strong&gt;4.7.0 (August 5, 2017)&lt;/strong&gt;&lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;&lt;strong&gt;New setting:&lt;/strong&gt; Browser &amp;gt; In-app browser for videos&lt;/li&gt;\\n&lt;li&gt;Play YouTube videos (requires YouTube app)&lt;/li&gt;\\n&lt;li&gt;Play v.redd.it videos&lt;/li&gt;\\n&lt;li&gt;Play MP4 and WebM videos&lt;/li&gt;\\n&lt;li&gt;Play Giphy as MP4 (faster and smaller)&lt;/li&gt;\\n&lt;li&gt;External browser URLs checkbox &amp;quot;Never in RIF&amp;quot; (Settings &amp;gt; Browser)&lt;/li&gt;\\n&lt;li&gt;Forward button when you press Back to go home&lt;/li&gt;\\n&lt;li&gt;Support Android O (8.0)&lt;/li&gt;\\n&lt;li&gt;Fixed saving Twitter images&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;hr/&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.1 fixes a bunch of FCs, mostly with YouTube and videos. Fixes saving Gfycat files. Also clarifies the &amp;quot;in-app&amp;quot; and &amp;quot;external&amp;quot; Browser settings descriptions.&lt;/p&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.2 fixes more FCs, fixes a comments UI bug, and possibly fixes a &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6ryycx/update_app_automatically_upvotesdownvotes_random/\\\"&gt;phantom vote bug&lt;/a&gt; on some devices.&lt;/p&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.3 fixes &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6s63gz/inapp_youtube_feature_doesnt_work_with_timestamps/\\\"&gt;loading YouTube timestamps&lt;/a&gt;, adds a video repeat toggle button (non-YouTube videos), improves video mute behavior, and fixes some rarer FCs including YouTube and downloadable themes.&lt;/p&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.4 updates third-party libraries for various bugfixes.&lt;/p&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.5 fixes &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6sx1hc/blurry_thumbnail_after_update/\\\"&gt;blurry card images&lt;/a&gt;, going back from &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6sxct6/following_a_deep_comment_thread_and_then_pressing/\\\"&gt;deep comments&lt;/a&gt;, and FCs when visiting certain malformed Imgur URLs. Will gradually roll out to everyone (out of beta).&lt;/p&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.6 fixes &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6t7xgj/inapp_video_player_softlock/\\\"&gt;YouTube fullscreen bugs&lt;/a&gt;, &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6s63gz/inapp_youtube_feature_doesnt_work_with_timestamps/dlhp2p2/\\\"&gt;more YouTube timestamps&lt;/a&gt;, a &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6t16ca/strange_bug_with_the_search_function/\\\"&gt;search UI bug&lt;/a&gt;, and falls back to &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6t1hty/error_loading_imgur_galleries/\\\"&gt;WebView for image albums&lt;/a&gt; if necessary.&lt;/p&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.7 &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6teooq/inapp_youtube_player_auto_rotates_from_full/\\\"&gt;locks rotation in fullscreen&lt;/a&gt;, and fixes playing videos &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6tdpn6/in_app_browser_for_vreddit_does_not_work_from/\\\"&gt;linked from a user profile&lt;/a&gt;.&lt;/p&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.8 fixes YouTube auto-landscape broken in 4.7.7, fixes opening &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6tn711/certain_imgur_galleries_not_opening_in_the_inapp/\\\"&gt;URLs with unusual capitalization&lt;/a&gt;, and adds a YouTube &amp;quot;External browser URLs&amp;quot; helper when you toggle &amp;quot;In-app browser for videos.&amp;quot; Released to everyone (out of beta).&lt;/p&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.9 fixes &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6u5qim/turning_off_screen_while_watching_video_inapp/\\\"&gt;remembering YouTube position&lt;/a&gt;, fixes &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6ulpw1/cant_rotate_videos/dluavqq/\\\"&gt;unnecessary extra Back press to exit fullscreen on WebView videos&lt;/a&gt;, the &lt;a href=\\\"https://www.reddit.com/r/redditisfun/comments/6unsbo/tumblrs_open_in_app_button_is_broken/\\\"&gt;Tumblr open in app button&lt;/a&gt;, and a few other minor UI bugs.&lt;/p&gt;\\n\\n&lt;p&gt;&lt;strong&gt;UPDATE:&lt;/strong&gt; Version 4.7.10 adds a button to rotate in fullscreen (except YouTube due to bugginess), and fixes homescreen widget auto refresh.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"selftext\": \"&gt;**4.7.0 (August 5, 2017)**\\n&gt;\\n&gt;* **New setting:** Browser &gt; In-app browser for videos\\n* Play YouTube videos (requires YouTube app)\\n* Play v.redd.it videos\\n* Play MP4 and WebM videos\\n* Play Giphy as MP4 (faster and smaller)\\n* External browser URLs checkbox \\\"Never in RIF\\\" (Settings &gt; Browser)\\n* Forward button when you press Back to go home\\n* Support Android O (8.0)\\n* Fixed saving Twitter images\\n\\n-----\\n\\n**UPDATE:** Version 4.7.1 fixes a bunch of FCs, mostly with YouTube and videos. Fixes saving Gfycat files. Also clarifies the \\\"in-app\\\" and \\\"external\\\" Browser settings descriptions.\\n\\n**UPDATE:** Version 4.7.2 fixes more FCs, fixes a comments UI bug, and possibly fixes a [phantom vote bug](https://www.reddit.com/r/redditisfun/comments/6ryycx/update_app_automatically_upvotesdownvotes_random/) on some devices.\\n\\n**UPDATE:** Version 4.7.3 fixes [loading YouTube timestamps](https://www.reddit.com/r/redditisfun/comments/6s63gz/inapp_youtube_feature_doesnt_work_with_timestamps/), adds a video repeat toggle button (non-YouTube videos), improves video mute behavior, and fixes some rarer FCs including YouTube and downloadable themes.\\n\\n**UPDATE:** Version 4.7.4 updates third-party libraries for various bugfixes.\\n\\n**UPDATE:** Version 4.7.5 fixes [blurry card images](https://www.reddit.com/r/redditisfun/comments/6sx1hc/blurry_thumbnail_after_update/), going back from [deep comments](https://www.reddit.com/r/redditisfun/comments/6sxct6/following_a_deep_comment_thread_and_then_pressing/), and FCs when visiting certain malformed Imgur URLs. Will gradually roll out to everyone (out of beta).\\n\\n**UPDATE:** Version 4.7.6 fixes [YouTube fullscreen bugs](https://www.reddit.com/r/redditisfun/comments/6t7xgj/inapp_video_player_softlock/), [more YouTube timestamps](https://www.reddit.com/r/redditisfun/comments/6s63gz/inapp_youtube_feature_doesnt_work_with_timestamps/dlhp2p2/), a [search UI bug](https://www.reddit.com/r/redditisfun/comments/6t16ca/strange_bug_with_the_search_function/), and falls back to [WebView for image albums](https://www.reddit.com/r/redditisfun/comments/6t1hty/error_loading_imgur_galleries/) if necessary.\\n\\n**UPDATE:** Version 4.7.7 [locks rotation in fullscreen](https://www.reddit.com/r/redditisfun/comments/6teooq/inapp_youtube_player_auto_rotates_from_full/), and fixes playing videos [linked from a user profile](https://www.reddit.com/r/redditisfun/comments/6tdpn6/in_app_browser_for_vreddit_does_not_work_from/).\\n\\n**UPDATE:** Version 4.7.8 fixes YouTube auto-landscape broken in 4.7.7, fixes opening [URLs with unusual capitalization](https://www.reddit.com/r/redditisfun/comments/6tn711/certain_imgur_galleries_not_opening_in_the_inapp/), and adds a YouTube \\\"External browser URLs\\\" helper when you toggle \\\"In-app browser for videos.\\\" Released to everyone (out of beta).\\n\\n**UPDATE:** Version 4.7.9 fixes [remembering YouTube position](https://www.reddit.com/r/redditisfun/comments/6u5qim/turning_off_screen_while_watching_video_inapp/), fixes [unnecessary extra Back press to exit fullscreen on WebView videos](https://www.reddit.com/r/redditisfun/comments/6ulpw1/cant_rotate_videos/dluavqq/), the [Tumblr open in app button](https://www.reddit.com/r/redditisfun/comments/6unsbo/tumblrs_open_in_app_button_is_broken/), and a few other minor UI bugs.\\n\\n**UPDATE:** Version 4.7.10 adds a button to rotate in fullscreen (except YouTube due to bugginess), and fixes homescreen widget auto refresh.\",\n" +
            "                    \"likes\": null,\n" +
            "                    \"suggested_sort\": null,\n" +
            "                    \"user_reports\": [ ],\n" +
            "                    \"secure_media\": null,\n" +
            "                    \"link_flair_text\": \"UPDATE: 4.7.10\",\n" +
            "                    \"id\": \"6rniin\",\n" +
            "                    \"banned_at_utc\": null,\n" +
            "                    \"view_count\": null,\n" +
            "                    \"archived\": false,\n" +
            "                    \"clicked\": false,\n" +
            "                    \"report_reasons\": null,\n" +
            "                    \"title\": \"reddit is fun 4.7.0 going into beta - in-app YouTube, v.redd.it, MP4, WebM links; Android O (8.0) support\",\n" +
            "                    \"num_crossposts\": 0,\n" +
            "                    \"saved\": false,\n" +
            "                    \"mod_reports\": [ ],\n" +
            "                    \"can_mod_post\": false,\n" +
            "                    \"is_crosspostable\": false,\n" +
            "                    \"score\": 149,\n" +
            "                    \"approved_by\": null,\n" +
            "                    \"over_18\": false,\n" +
            "                    \"hidden\": false,\n" +
            "                    \"thumbnail\": \"self\",\n" +
            "                    \"subreddit_id\": \"t5_2rfi7\",\n" +
            "                    \"edited\": 1503557955.0,\n" +
            "                    \"link_flair_css_class\": \"\",\n" +
            "                    \"author_flair_css_class\": \"official\",\n" +
            "                    \"contest_mode\": false,\n" +
            "                    \"gilded\": 0,\n" +
            "                    \"downs\": 0,\n" +
            "                    \"brand_safe\": false,\n" +
            "                    \"secure_media_embed\": { },\n" +
            "                    \"removal_reason\": null,\n" +
            "                    \"author_flair_text\": \"RIF Dev\",\n" +
            "                    \"stickied\": true,\n" +
            "                    \"can_gild\": true,\n" +
            "                    \"thumbnail_height\": null,\n" +
            "                    \"parent_whitelist_status\": null,\n" +
            "                    \"name\": \"t3_6rniin\",\n" +
            "                    \"spoiler\": false,\n" +
            "                    \"permalink\": \"/r/redditisfun/comments/6rniin/reddit_is_fun_470_going_into_beta_inapp_youtube/\",\n" +
            "                    \"subreddit_type\": \"public\",\n" +
            "                    \"locked\": false,\n" +
            "                    \"hide_score\": false,\n" +
            "                    \"created\": 1501909672.0,\n" +
            "                    \"url\": \"https://www.reddit.com/r/redditisfun/comments/6rniin/reddit_is_fun_470_going_into_beta_inapp_youtube/\",\n" +
            "                    \"whitelist_status\": null,\n" +
            "                    \"quarantine\": false,\n" +
            "                    \"author\": \"talklittle\",\n" +
            "                    \"created_utc\": 1501880872.0,\n" +
            "                    \"subreddit_name_prefixed\": \"r/redditisfun\",\n" +
            "                    \"ups\": 149,\n" +
            "                    \"media\": null,\n" +
            "                    \"num_comments\": 71,\n" +
            "                    \"is_self\": true,\n" +
            "                    \"visited\": false,\n" +
            "                    \"num_reports\": null,\n" +
            "                    \"is_video\": false,\n" +
            "                    \"distinguished\": null\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"kind\": \"t3\",\n" +
            "                \"data\": {\n" +
            "                    \"domain\": \"self.redditisfun\",\n" +
            "                    \"approved_at_utc\": null,\n" +
            "                    \"banned_by\": null,\n" +
            "                    \"media_embed\": { },\n" +
            "                    \"thumbnail_width\": null,\n" +
            "                    \"subreddit\": \"redditisfun\",\n" +
            "                    \"selftext_html\": null,\n" +
            "                    \"selftext\": \"\",\n" +
            "                    \"likes\": null,\n" +
            "                    \"suggested_sort\": null,\n" +
            "                    \"user_reports\": [ ],\n" +
            "                    \"secure_media\": null,\n" +
            "                    \"link_flair_text\": \"Suggestion/Idea\",\n" +
            "                    \"id\": \"6w6e6v\",\n" +
            "                    \"banned_at_utc\": null,\n" +
            "                    \"view_count\": null,\n" +
            "                    \"archived\": false,\n" +
            "                    \"clicked\": false,\n" +
            "                    \"report_reasons\": null,\n" +
            "                    \"title\": \"Can we copy parts of posts?\",\n" +
            "                    \"num_crossposts\": 0,\n" +
            "                    \"saved\": false,\n" +
            "                    \"mod_reports\": [ ],\n" +
            "                    \"can_mod_post\": false,\n" +
            "                    \"is_crosspostable\": false,\n" +
            "                    \"score\": 18,\n" +
            "                    \"approved_by\": null,\n" +
            "                    \"over_18\": false,\n" +
            "                    \"hidden\": false,\n" +
            "                    \"thumbnail\": \"self\",\n" +
            "                    \"subreddit_id\": \"t5_2rfi7\",\n" +
            "                    \"edited\": false,\n" +
            "                    \"link_flair_css_class\": \"suggestion\",\n" +
            "                    \"author_flair_css_class\": null,\n" +
            "                    \"contest_mode\": false,\n" +
            "                    \"gilded\": 0,\n" +
            "                    \"downs\": 0,\n" +
            "                    \"brand_safe\": false,\n" +
            "                    \"secure_media_embed\": { },\n" +
            "                    \"removal_reason\": null,\n" +
            "                    \"author_flair_text\": null,\n" +
            "                    \"stickied\": false,\n" +
            "                    \"can_gild\": true,\n" +
            "                    \"thumbnail_height\": null,\n" +
            "                    \"parent_whitelist_status\": null,\n" +
            "                    \"name\": \"t3_6w6e6v\",\n" +
            "                    \"spoiler\": false,\n" +
            "                    \"permalink\": \"/r/redditisfun/comments/6w6e6v/can_we_copy_parts_of_posts/\",\n" +
            "                    \"subreddit_type\": \"public\",\n" +
            "                    \"locked\": false,\n" +
            "                    \"hide_score\": false,\n" +
            "                    \"created\": 1503792864.0,\n" +
            "                    \"url\": \"https://www.reddit.com/r/redditisfun/comments/6w6e6v/can_we_copy_parts_of_posts/\",\n" +
            "                    \"whitelist_status\": null,\n" +
            "                    \"quarantine\": false,\n" +
            "                    \"author\": \"not_a_timetraveler\",\n" +
            "                    \"created_utc\": 1503764064.0,\n" +
            "                    \"subreddit_name_prefixed\": \"r/redditisfun\",\n" +
            "                    \"ups\": 18,\n" +
            "                    \"media\": null,\n" +
            "                    \"num_comments\": 4,\n" +
            "                    \"is_self\": true,\n" +
            "                    \"visited\": false,\n" +
            "                    \"num_reports\": null,\n" +
            "                    \"is_video\": false,\n" +
            "                    \"distinguished\": null\n" +
            "                }\n" +
            "            }\n" +
            "        ],\n" +
            "        \"after\": \"t3_6v9kj2\",\n" +
            "        \"before\": null\n" +
            "    }\n" +
            "\n" +
            "}\n";

    static String aPost = "{\n" +
            "                    \"domain\": \"i.imgur.com\",\n" +
            "                    \"approved_at_utc\": null,\n" +
            "                    \"banned_by\": null,\n" +
            "                    \"media_embed\": { },\n" +
            "                    \"thumbnail_width\": 140,\n" +
            "                    \"subreddit\": \"comics\",\n" +
            "                    \"selftext_html\": null,\n" +
            "                    \"selftext\": \"\",\n" +
            "                    \"likes\": null,\n" +
            "                    \"suggested_sort\": null,\n" +
            "                    \"user_reports\": [ ],\n" +
            "                    \"secure_media\": null,\n" +
            "                    \"link_flair_text\": null,\n" +
            "                    \"id\": \"6w5e1j\",\n" +
            "                    \"banned_at_utc\": null,\n" +
            "                    \"view_count\": null,\n" +
            "                    \"archived\": false,\n" +
            "                    \"clicked\": false,\n" +
            "                    \"report_reasons\": null,\n" +
            "                    \"title\": \"The Perfect Blend [OC]\",\n" +
            "                    \"num_crossposts\": 0,\n" +
            "                    \"saved\": false,\n" +
            "                    \"mod_reports\": [ ],\n" +
            "                    \"can_mod_post\": false,\n" +
            "                    \"is_crosspostable\": false,\n" +
            "                    \"score\": 25928,\n" +
            "                    \"approved_by\": null,\n" +
            "                    \"over_18\": false,\n" +
            "                    \"hidden\": false,\n" +
            "                    \"preview\": {\n" +
            "                        \"images\": [\n" +
            "                            {\n" +
            "                                \"source\": {\n" +
            "                                    \"url\": \"https://i.redditmedia.com/0yn0-2GouonziGOH463Dq4yf3mAPd4lH3o_1G0UMy-0.jpg?s=7fd861d132d928baf7fac5148e10d86b\",\n" +
            "                                    \"width\": 1890,\n" +
            "                                    \"height\": 2899\n" +
            "                                },\n" +
            "                                \"resolutions\": [\n" +
            "                                    {\n" +
            "                                        \"url\": \"https://i.redditmedia.com/0yn0-2GouonziGOH463Dq4yf3mAPd4lH3o_1G0UMy-0.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;s=ac4fb4999f509b62d26e2d0c6245fc06\",\n" +
            "                                        \"width\": 108,\n" +
            "                                        \"height\": 165\n" +
            "                                    },\n" +
            "                                    {\n" +
            "                                        \"url\": \"https://i.redditmedia.com/0yn0-2GouonziGOH463Dq4yf3mAPd4lH3o_1G0UMy-0.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=216&amp;s=076094d68402b83b1735bebc12741fed\",\n" +
            "                                        \"width\": 216,\n" +
            "                                        \"height\": 331\n" +
            "                                    },\n" +
            "                                    {\n" +
            "                                        \"url\": \"https://i.redditmedia.com/0yn0-2GouonziGOH463Dq4yf3mAPd4lH3o_1G0UMy-0.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;s=58b4648309e6930ca7449bc00db8fe9b\",\n" +
            "                                        \"width\": 320,\n" +
            "                                        \"height\": 490\n" +
            "                                    },\n" +
            "                                    {\n" +
            "                                        \"url\": \"https://i.redditmedia.com/0yn0-2GouonziGOH463Dq4yf3mAPd4lH3o_1G0UMy-0.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=640&amp;s=ce0d81cf38c859ebb1e9f4b9d54dd8b4\",\n" +
            "                                        \"width\": 640,\n" +
            "                                        \"height\": 981\n" +
            "                                    },\n" +
            "                                    {\n" +
            "                                        \"url\": \"https://i.redditmedia.com/0yn0-2GouonziGOH463Dq4yf3mAPd4lH3o_1G0UMy-0.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=960&amp;s=013cf5d98c8872afbb50cbd693d1dcaf\",\n" +
            "                                        \"width\": 960,\n" +
            "                                        \"height\": 1472\n" +
            "                                    },\n" +
            "                                    {\n" +
            "                                        \"url\": \"https://i.redditmedia.com/0yn0-2GouonziGOH463Dq4yf3mAPd4lH3o_1G0UMy-0.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=1080&amp;s=0821a3d856c072505f32a6648abcf80a\",\n" +
            "                                        \"width\": 1080,\n" +
            "                                        \"height\": 1656\n" +
            "                                    }\n" +
            "                                ],\n" +
            "                                \"variants\": { },\n" +
            "                                \"id\": \"2ljROTzLEhBEwkbKqSYzfeCrw1mx6ajbd64sfwmp5Bk\"\n" +
            "                            }\n" +
            "                        ],\n" +
            "                        \"enabled\": true\n" +
            "                    },\n" +
            "                    \"thumbnail\": \"https://b.thumbs.redditmedia.com/MYVii2Ou0_f9_wiJ7dsMHnng9OCSL0cOKfYYWwUeACE.jpg\",\n" +
            "                    \"subreddit_id\": \"t5_2qh0s\",\n" +
            "                    \"edited\": false,\n" +
            "                    \"link_flair_css_class\": null,\n" +
            "                    \"author_flair_css_class\": null,\n" +
            "                    \"contest_mode\": false,\n" +
            "                    \"gilded\": 0,\n" +
            "                    \"downs\": 0,\n" +
            "                    \"brand_safe\": true,\n" +
            "                    \"secure_media_embed\": { },\n" +
            "                    \"removal_reason\": null,\n" +
            "                    \"post_hint\": \"image\",\n" +
            "                    \"author_flair_text\": null,\n" +
            "                    \"stickied\": false,\n" +
            "                    \"can_gild\": true,\n" +
            "                    \"thumbnail_height\": 140,\n" +
            "                    \"parent_whitelist_status\": \"all_ads\",\n" +
            "                    \"name\": \"t3_6w5e1j\",\n" +
            "                    \"spoiler\": false,\n" +
            "                    \"permalink\": \"/r/comics/comments/6w5e1j/the_perfect_blend_oc/\",\n" +
            "                    \"subreddit_type\": \"public\",\n" +
            "                    \"locked\": false,\n" +
            "                    \"hide_score\": false,\n" +
            "                    \"created\": 1503780854.0,\n" +
            "                    \"url\": \"http://i.imgur.com/rQ4Uh72.jpg\",\n" +
            "                    \"whitelist_status\": \"all_ads\",\n" +
            "                    \"quarantine\": false,\n" +
            "                    \"author\": \"Raphcomics\",\n" +
            "                    \"created_utc\": 1503752054.0,\n" +
            "                    \"subreddit_name_prefixed\": \"r/comics\",\n" +
            "                    \"ups\": 25928,\n" +
            "                    \"media\": null,\n" +
            "                    \"num_comments\": 187,\n" +
            "                    \"is_self\": false,\n" +
            "                    \"visited\": false,\n" +
            "                    \"num_reports\": null,\n" +
            "                    \"is_video\": false,\n" +
            "                    \"distinguished\": null\n" +
            "                }";
    static String aSubreddit = "{\n" +
            "                    \"user_is_contributor\": false,\n" +
            "                    \"banner_img\": \"\",\n" +
            "                    \"user_flair_text\": null,\n" +
            "                    \"submit_text_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;&lt;strong&gt;If You Are A Comic Artist&lt;/strong&gt;    &lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;Comic artists are welcome to post their own work any way they want.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;If you don&amp;#39;t want to link to your website, put [OC] in your post title (it means &amp;quot;Original Content&amp;quot;) or &lt;a href=\\\"/message/compose?to=%2Fr%2Fcomics\\\"&gt;request Artist Flair&lt;/a&gt;.&lt;/li&gt;\\n&lt;/ul&gt;\\n\\n&lt;p&gt;&lt;strong&gt;If You Are Not An Artist&lt;/strong&gt;  &lt;/p&gt;\\n\\n&lt;ul&gt;\\n&lt;li&gt;Link to an original source like the artist&amp;#39;s website or a syndication site.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;Link to the page, not the image file, so the artist gets the credit and site traffic.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;Imgur/rehost links are ok for translations, but you must also state clearly it is a translation and link to the non-English source in a comment.&lt;br/&gt;&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"user_is_banned\": false,\n" +
            "                    \"wiki_enabled\": true,\n" +
            "                    \"show_media\": true,\n" +
            "                    \"id\": \"2qh0s\",\n" +
            "                    \"description\": \"##About \\\\/r/Comics\\n\\n&gt; This subreddit is for everything related to print comics and web comics.  Artists are encouraged to post their own work.  News and media for adaptations based on comic books are welcome. Read [the subreddit wiki](https://www.reddit.com/r/comics/wiki/index) for more information about the subreddit.\\n\\n\\n##[General Conduct](/r/Comics/wiki/index#wiki_general_conduct)\\n\\n&gt; 1. **[Don't complain about comics you don't like or understand.](/r/Comics/wiki/index#wiki_1._don.27t_complain_about_comics_you_don.27t_like.)** - If you don't get a comic, it's probably not meant for you.  Don't whine about it; just move on to the next comic.\\n2. **[Don't attack artists posting their content.](/r/Comics/wiki/index#wiki_2._don.27t_attack_artists_posting_their_content)** - It's not ok to attack artists with hateful comments meant to tear them down or try to chase them off Reddit.\\n3. **[Don't make drama for drama's sake. No trolling.](/r/Comics/wiki/index#wiki_3._don.27t_make_drama_for_drama.27s_sake._no_trolling.)** - Anyone wanting to participate in this community needs to keep their comments civil.\\n4. **[Respect the [OC] System](/r/Comics/wiki/index#wiki_4._respect_the_.5Boc.5D_system)** - The [OC] tag is for artists wanting to submit their Original Content.  False claims on others' art will get you banned.\\n5. **[Respect the Report System](/r/Comics/wiki/index#wiki_5._respect_the_report_system)** - Reports are for content that breaks this sub's/Reddit's rules.  False reports are given to the Reddit Admins. \\n6. **[Tag NSFW content when needed.](/r/Comics/wiki/index#wiki_6._tag_nsfw_content_when_needed.)** - Content with nudity, pornography, or obvious profanity should be tagged as NSFW.  \\n\\n##[What is OK to Post](/r/Comics/wiki/index#wiki_what_is_ok_to_post)\\n\\n&gt; * **Comic covers or short excerpts** - *A few* scanned pages can be posted for discussion.  Don't post entire comic books.  Posts with too many pages will get removed.\\n* **Web comic strips** - Most strips need to be linked back to original sources (i.e. the artists' websites).   Don't link to [rehosted](/r/Comics/wiki/index#wiki_what_is_rehosting) or [hotlinked](/r/Comics/wiki/index#wiki_what_is_hotlinking) comics unless they're in the [exception whitelist](/r/Comics/wiki/index#wiki_established_comic_whitelist).  Artists can use [OC] to link to their own work anywhere.\\n* **Links to articles, videos, art, cosplay or music** - Content directly related to comic books or web comics, including film and TV media.  \\n* **Discussion topics** - Talk about past or present comic books or strips.  Topics about comic-based movies or TV shows are also fine.\\n* **Translations** - English translations of non-English original comics are ok as long as the translation is honest and a link to the original source is provided in a comment.\\n\\n##[What is not OK to Post](/r/Comics/wiki/index#wiki_what_is_not_ok_to_post)\\n\\n&gt; * **Edits to comics that you didn't make** - Don't remove watermarks/attribution or try to \\\"fix\\\" someone else's work.  Post your own original content.\\n* **Stuff that just reminds you of comics without there being any real reference in the topic** - Please keep posts on topic with real comic content.\\n* **Don't put the punchline in the post title** - Links to humor comics that are submitted with joke-ruining titles may be removed.  \\n* **Links to download complete, pirated comic books** - Please support comics by buying them.\\n* **Shitposts** --  Low-effort stick-figure/MS Paint drawing/meme or other lazy/meta/circlejerk content is not welcome. Don't post rage comics, non-comic memes, or CAPTCHAs\\n\\n##[Are You A Comic Artist?](/r/Comics/wiki/index#wiki_are_you_a_comic_artist.3F)\\n\\n&gt; Content creators can get Artist Flair to display next to their account names on all of their posts. [Message the moderators](/message/compose?to=%2Fr%2FComics) with some proof and the name of your strip for some blue flair.  Artists without flair can add [OC] to post titles to identify their \\\"Original Content.\\\" Tagging a post with [OC] allows links to general hosting sites like Imgur.\\n\\n&gt; &amp;nbsp;\\n    \\n&gt; [ ](http://www.reddit.com/message/compose?to=%23comics \\\"flair-talk\\\")\\n\\n##Related Subreddits\\n\\n&gt; * /r/comicbooks\\n* /r/webcomics\\n* /r/manga\\n* /r/graphicnovels\\n* /r/comicbookart\\n* /r/comiccon\\n* /r/comic_crits\\n* /r/wholesomecomics \\n* [Marvel Subreddits](/r/comics/wiki/marvel)\\n* [DC Subreddits](/r/comics/wiki/dc)\\n* [Image Subreddits](/r/comics/wiki/image)\\n* [Vertigo Subreddits](/r/comics/wiki/vertigo)\\n* [Dark Horse Subreddits](/r/comics/wiki/darkhorse)\\n* [Valiant Subreddits](/r/comics/wiki/valiant)\\n* [Manga Subreddits](/r/comics/wiki/manga)\\n* [Other Comics-Related Subreddits](/r/comics/wiki/other)\\n\\n\\n\",\n" +
            "                    \"submit_text\": \"**If You Are A Comic Artist**    \\n\\n* Comic artists are welcome to post their own work any way they want.   \\n* If you don't want to link to your website, put [OC] in your post title (it means \\\"Original Content\\\") or [request Artist Flair](/message/compose?to=%2Fr%2Fcomics).\\n\\n**If You Are Not An Artist**  \\n  \\n* Link to an original source like the artist's website or a syndication site.  \\n* Link to the page, not the image file, so the artist gets the credit and site traffic.         \\n* Imgur/rehost links are ok for translations, but you must also state clearly it is a translation and link to the non-English source in a comment.  \",\n" +
            "                    \"user_can_flair_in_sr\": null,\n" +
            "                    \"display_name\": \"comics\",\n" +
            "                    \"header_img\": \"https://c.thumbs.redditmedia.com/uIbyX8MSVa0J6mKV.png\",\n" +
            "                    \"description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;h2&gt;About /r/Comics&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;p&gt;This subreddit is for everything related to print comics and web comics.  Artists are encouraged to post their own work.  News and media for adaptations based on comic books are welcome. Read &lt;a href=\\\"https://www.reddit.com/r/comics/wiki/index\\\"&gt;the subreddit wiki&lt;/a&gt; for more information about the subreddit.&lt;/p&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_general_conduct\\\"&gt;General Conduct&lt;/a&gt;&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;ol&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_1._don.27t_complain_about_comics_you_don.27t_like.\\\"&gt;Don&amp;#39;t complain about comics you don&amp;#39;t like or understand.&lt;/a&gt;&lt;/strong&gt; - If you don&amp;#39;t get a comic, it&amp;#39;s probably not meant for you.  Don&amp;#39;t whine about it; just move on to the next comic.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_2._don.27t_attack_artists_posting_their_content\\\"&gt;Don&amp;#39;t attack artists posting their content.&lt;/a&gt;&lt;/strong&gt; - It&amp;#39;s not ok to attack artists with hateful comments meant to tear them down or try to chase them off Reddit.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_3._don.27t_make_drama_for_drama.27s_sake._no_trolling.\\\"&gt;Don&amp;#39;t make drama for drama&amp;#39;s sake. No trolling.&lt;/a&gt;&lt;/strong&gt; - Anyone wanting to participate in this community needs to keep their comments civil.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_4._respect_the_.5Boc.5D_system\\\"&gt;Respect the [OC] System&lt;/a&gt;&lt;/strong&gt; - The [OC] tag is for artists wanting to submit their Original Content.  False claims on others&amp;#39; art will get you banned.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_5._respect_the_report_system\\\"&gt;Respect the Report System&lt;/a&gt;&lt;/strong&gt; - Reports are for content that breaks this sub&amp;#39;s/Reddit&amp;#39;s rules.  False reports are given to the Reddit Admins. &lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_6._tag_nsfw_content_when_needed.\\\"&gt;Tag NSFW content when needed.&lt;/a&gt;&lt;/strong&gt; - Content with nudity, pornography, or obvious profanity should be tagged as NSFW.&lt;br/&gt;&lt;/li&gt;\\n&lt;/ol&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_what_is_ok_to_post\\\"&gt;What is OK to Post&lt;/a&gt;&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;ul&gt;\\n&lt;li&gt;&lt;strong&gt;Comic covers or short excerpts&lt;/strong&gt; - &lt;em&gt;A few&lt;/em&gt; scanned pages can be posted for discussion.  Don&amp;#39;t post entire comic books.  Posts with too many pages will get removed.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Web comic strips&lt;/strong&gt; - Most strips need to be linked back to original sources (i.e. the artists&amp;#39; websites).   Don&amp;#39;t link to &lt;a href=\\\"/r/Comics/wiki/index#wiki_what_is_rehosting\\\"&gt;rehosted&lt;/a&gt; or &lt;a href=\\\"/r/Comics/wiki/index#wiki_what_is_hotlinking\\\"&gt;hotlinked&lt;/a&gt; comics unless they&amp;#39;re in the &lt;a href=\\\"/r/Comics/wiki/index#wiki_established_comic_whitelist\\\"&gt;exception whitelist&lt;/a&gt;.  Artists can use [OC] to link to their own work anywhere.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Links to articles, videos, art, cosplay or music&lt;/strong&gt; - Content directly related to comic books or web comics, including film and TV media.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Discussion topics&lt;/strong&gt; - Talk about past or present comic books or strips.  Topics about comic-based movies or TV shows are also fine.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Translations&lt;/strong&gt; - English translations of non-English original comics are ok as long as the translation is honest and a link to the original source is provided in a comment.&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_what_is_not_ok_to_post\\\"&gt;What is not OK to Post&lt;/a&gt;&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;ul&gt;\\n&lt;li&gt;&lt;strong&gt;Edits to comics that you didn&amp;#39;t make&lt;/strong&gt; - Don&amp;#39;t remove watermarks/attribution or try to &amp;quot;fix&amp;quot; someone else&amp;#39;s work.  Post your own original content.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Stuff that just reminds you of comics without there being any real reference in the topic&lt;/strong&gt; - Please keep posts on topic with real comic content.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Don&amp;#39;t put the punchline in the post title&lt;/strong&gt; - Links to humor comics that are submitted with joke-ruining titles may be removed.&lt;br/&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Links to download complete, pirated comic books&lt;/strong&gt; - Please support comics by buying them.&lt;/li&gt;\\n&lt;li&gt;&lt;strong&gt;Shitposts&lt;/strong&gt; --  Low-effort stick-figure/MS Paint drawing/meme or other lazy/meta/circlejerk content is not welcome. Don&amp;#39;t post rage comics, non-comic memes, or CAPTCHAs&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;&lt;a href=\\\"/r/Comics/wiki/index#wiki_are_you_a_comic_artist.3F\\\"&gt;Are You A Comic Artist?&lt;/a&gt;&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;p&gt;Content creators can get Artist Flair to display next to their account names on all of their posts. &lt;a href=\\\"/message/compose?to=%2Fr%2FComics\\\"&gt;Message the moderators&lt;/a&gt; with some proof and the name of your strip for some blue flair.  Artists without flair can add [OC] to post titles to identify their &amp;quot;Original Content.&amp;quot; Tagging a post with [OC] allows links to general hosting sites like Imgur.&lt;/p&gt;\\n\\n&lt;p&gt;&amp;nbsp;&lt;/p&gt;\\n\\n&lt;p&gt;&lt;a href=\\\"http://www.reddit.com/message/compose?to=%23comics\\\" title=\\\"flair-talk\\\"&gt; &lt;/a&gt;&lt;/p&gt;\\n&lt;/blockquote&gt;\\n\\n&lt;h2&gt;Related Subreddits&lt;/h2&gt;\\n\\n&lt;blockquote&gt;\\n&lt;ul&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comicbooks\\\"&gt;/r/comicbooks&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/webcomics\\\"&gt;/r/webcomics&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/manga\\\"&gt;/r/manga&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/graphicnovels\\\"&gt;/r/graphicnovels&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comicbookart\\\"&gt;/r/comicbookart&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comiccon\\\"&gt;/r/comiccon&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comic_crits\\\"&gt;/r/comic_crits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/wholesomecomics\\\"&gt;/r/wholesomecomics&lt;/a&gt; &lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/marvel\\\"&gt;Marvel Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/dc\\\"&gt;DC Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/image\\\"&gt;Image Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/vertigo\\\"&gt;Vertigo Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/darkhorse\\\"&gt;Dark Horse Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/valiant\\\"&gt;Valiant Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/manga\\\"&gt;Manga Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;li&gt;&lt;a href=\\\"/r/comics/wiki/other\\\"&gt;Other Comics-Related Subreddits&lt;/a&gt;&lt;/li&gt;\\n&lt;/ul&gt;\\n&lt;/blockquote&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"title\": \"Print Comics and Webcomics \",\n" +
            "                    \"collapse_deleted_comments\": true,\n" +
            "                    \"user_has_favorited\": false,\n" +
            "                    \"public_description\": \"Everything related to print comics (comic books, graphic novels, and strips) and web comics.  Artists are encouraged to post their own work.  News and media for adaptations based on comic books are welcome. Read [the subreddit wiki](https://www.reddit.com/r/comics/wiki/index) for more information about the subreddit.\",\n" +
            "                    \"over18\": false,\n" +
            "                    \"public_description_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;Everything related to print comics (comic books, graphic novels, and strips) and web comics.  Artists are encouraged to post their own work.  News and media for adaptations based on comic books are welcome. Read &lt;a href=\\\"https://www.reddit.com/r/comics/wiki/index\\\"&gt;the subreddit wiki&lt;/a&gt; for more information about the subreddit.&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\",\n" +
            "                    \"allow_videos\": true,\n" +
            "                    \"spoilers_enabled\": true,\n" +
            "                    \"icon_size\": null,\n" +
            "                    \"audience_target\": \"comics_hobbies,humor\",\n" +
            "                    \"suggested_comment_sort\": null,\n" +
            "                    \"active_user_count\": null,\n" +
            "                    \"icon_img\": \"\",\n" +
            "                    \"header_title\": \"300,000 subscribers! Thanks to JimKB for the logo!\",\n" +
            "                    \"display_name_prefixed\": \"r/comics\",\n" +
            "                    \"user_is_muted\": false,\n" +
            "                    \"submit_link_label\": null,\n" +
            "                    \"accounts_active\": null,\n" +
            "                    \"public_traffic\": false,\n" +
            "                    \"header_size\": [\n" +
            "                        175,\n" +
            "                        40\n" +
            "                    ],\n" +
            "                    \"subscribers\": 723382,\n" +
            "                    \"user_flair_css_class\": null,\n" +
            "                    \"submit_text_label\": null,\n" +
            "                    \"key_color\": \"#0079d3\",\n" +
            "                    \"user_sr_flair_enabled\": null,\n" +
            "                    \"lang\": \"en\",\n" +
            "                    \"is_enrolled_in_new_modmail\": null,\n" +
            "                    \"whitelist_status\": \"all_ads\",\n" +
            "                    \"name\": \"t5_2qh0s\",\n" +
            "                    \"user_flair_enabled_in_sr\": false,\n" +
            "                    \"created\": 1201248652.0,\n" +
            "                    \"url\": \"/r/comics/\",\n" +
            "                    \"quarantine\": false,\n" +
            "                    \"hide_ads\": false,\n" +
            "                    \"created_utc\": 1201219852.0,\n" +
            "                    \"banner_size\": null,\n" +
            "                    \"user_is_moderator\": false,\n" +
            "                    \"accounts_active_is_fuzzed\": false,\n" +
            "                    \"advertiser_category\": \"Entertainment\",\n" +
            "                    \"user_sr_theme_enabled\": true,\n" +
            "                    \"link_flair_enabled\": true,\n" +
            "                    \"allow_images\": true,\n" +
            "                    \"show_media_preview\": true,\n" +
            "                    \"comment_score_hide_mins\": 0,\n" +
            "                    \"subreddit_type\": \"public\",\n" +
            "                    \"submission_type\": \"any\",\n" +
            "                    \"user_is_subscriber\": true\n" +
            "                }" ;
                public static void testGsonParser(Context context)
    {
        ArrayList<Post> posts;
        getSubredditFromJson(aSubreddit);
        getPostFromJson(aPost);
        getSubredditsFromJsonUsingGsonParser(subredditsMineSubscriberJson);
        posts = getPostsFromJsonUsingGsonParser(rredditisfunjson);
        getMeFromJsonUsingGsonParser(apiV1Me);
        for(int i = 0; i < 100 ; i++) {
            persistPost(context, posts.get(0));
            persistPost(context, posts.get(1));
        }

    }

            */
    //========================================================Begin Gson Parsing===========================================================================
    public static Subreddit getSubredditFromJson(String js) {
        Gson gson = new Gson();
        Subreddit subreddit = gson.fromJson(js, Subreddit.class);
         /*
        if(subreddit != null) {

            Timber.d("id:" + subreddit.getId());
            Timber.d("title:" + subreddit.getTitle());
            //Timber.d("description:" + subreddit.getDescription());
            Timber.d("display_name:" + subreddit.getDisplay_name());
            Timber.d("display_name_prefixed:" + subreddit.getDisplay_name_prefixed());
            Timber.d("header_img:" + subreddit.getHeader_img());
            Timber.d("lang:" + subreddit.getLang());
            Timber.d("name:" + subreddit.getName());
        }
        */

        return  subreddit;
    }
    public static Post getPostFromJson(String js) {
        Gson gson = new Gson();
        Post post = gson.fromJson(js, Post.class);
        /*
        if(post != null) {

            Timber.d("id:" + post.getId());
            Timber.d("title:" + post.getTitle());
            Timber.d("subreddit:" + post.getSubreddit());
            Timber.d("subreddit_type:" + post.getSubreddit_type());
            Timber.d("permalink:" + post.getPermalink());
            Timber.d("url:" + post.getUrl());
            Timber.d("thumbnail:" + post.getThumbnail());
            Timber.d("ups:" + post.getUps());
            Timber.d("downs:" + post.getDowns());
            Timber.d("is_video:" + post.getIs_video());
            Timber.d("created:" + post.getCreated());

        }
*/
        return post;

    }
    public static ArrayList<Subreddit> getSubredditsFromJsonUsingGsonParser(String subreddits_mine_subscriber_json) {
        ArrayList<Subreddit> als = null;
        Subreddit3 subreddit3 = null;
        Gson gson = new Gson();
        //Timber.d(subreddits_mine_subscriber_json);
        try {
            subreddit3 = gson.fromJson(subreddits_mine_subscriber_json, Subreddit3.class);
        }catch (com.google.gson.JsonSyntaxException jse) {
            Timber.d(jse);
        } catch(java.lang.IllegalStateException ise) {
            Timber.d(ise);
        } catch (Exception e) {
            Timber.d(e);
        }

        if(subreddit3 != null) {
            int sizeAls = subreddit3.getData().children.length;
            als = new ArrayList<Subreddit>();
            for(int i = 0; i < sizeAls; i++)  {
                als.add(subreddit3.getData().children[i].getData());
            }

        }
        return als;
    }
    public static ArrayList<Post> getPostsFromJsonUsingGsonParser(String r_subreddit_json) {
        ArrayList<Post> alp = null;
        Post3 post3 = null;
        Gson gson = new Gson();
        try {
            post3 = gson.fromJson(r_subreddit_json, Post3.class);
        }catch (com.google.gson.JsonSyntaxException jse) {
            Timber.d(jse);
        } catch(java.lang.IllegalStateException ise) {
            Timber.d(ise);
        } catch (Exception e) {
            Timber.d(e);
        }
        if (post3 != null) {
            int sizeAlp = post3.getData().children.length;
            alp = new ArrayList<Post>();
            for(int i = 0; i < sizeAlp; i++)  {
                //Timber.d(post3.getData().children[i].getData().getPermalink());
                //Timber.d(post3.getData().children[i].getData().getUrl());
                alp.add(post3.getData().children[i].getData());
            }

        }
        return alp;
    }
    public static Me getMeFromJsonUsingGsonParser(String apiV1Me) {
        Gson gson = new Gson();
        /*Timber.d("=====================================================getMeFromJsonUsingGsonParser");
        Timber.d(apiV1Me);
        Timber.d("=====================================================getMeFromJsonUsingGsonParser");
        */
        Me me = gson.fromJson(apiV1Me, Me.class);
/*
        if(me != null) {

            Timber.d("=====================================================1");
            Log.d("Me", me.getId());
            Timber.d("=====================================================2");
            Log.d("Me", me.getName());
            Timber.d("=====================================================3");
            Log.d("Me", me.getCreated());
            Timber.d("=====================================================4");
            Log.d("Me", me.getCreated_utc());
            Timber.d("=====================================================5");
            Log.d("Me", me.getHas_verified_email());
            Timber.d("=====================================================6");
            Log.d("Me", me.getOver_18());
            Timber.d("=====================================================7");
            Log.d("Me", me.getOauth_client_id());
            Timber.d("=====================================================8");
        }  else {
            Timber.d("=====================================================9");
            Timber.d("Parsing Error!!");
            Timber.d("=====================================================10");

        }
*/
        return me;

    }

}
