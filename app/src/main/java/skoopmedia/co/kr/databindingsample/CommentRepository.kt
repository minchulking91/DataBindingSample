package skoopmedia.co.kr.databindingsample

import android.util.Log
import skoopmedia.co.kr.databindingsample.network.ApiManager

/**
 * Created by MinChulKim on 2017-09-29.
 */
class CommentRepository {
    private val commentList: MutableList<Comment> = mutableListOf()

    fun getCommentsFromServer(onFinish:(()->Unit)? = null) {
        ApiManager.instance.getComments(
                onResponse = { newList ->
                    commentList.clear()
                    commentList.addAll(newList)
                    onFinish?.invoke()
                },
                onError = { e ->
                    Log.e("CommentRepository", e.toString())
                    onFinish?.invoke()
                }
        )
    }

    fun getCommentList(): List<Comment> {
        return commentList
    }

    object LazyHolder {
        val INSTANCE = CommentRepository()
    }

    companion object {
        val instance: CommentRepository by lazy { LazyHolder.INSTANCE }
    }
}