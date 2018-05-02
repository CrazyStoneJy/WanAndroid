package me.crazystone.study.wanandroid.entity

/**
 * Created by crazy_stone on 18-4-28.
 */
data class ArticleDetailEntity (
        var apkLink: String,
        var author: String,
        var chapterId: Int,
        var chapterName: String,
        var collect: Boolean,
        var courseId: Int,
        var desc: String,
        var envelopePic: String,
        var fresh: Boolean,
        var id: Int,
        var link: String,
        var niceDate: String,
        var origin: String,
        var projectLink: String,
        var publishTime: Long,
        var superChapterId: Int,
        var superChapterName: String,
        var tags: List<Tag>,
        var title: String,
        var type: Int,
        var visible: Int,
        var zan: Int
)

data class Tag(
        var name: String,
        var url: String
)