package skoopmedia.co.kr.databindingsample

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.gson.annotations.SerializedName

/**
 * Created by MinChulKim on 2017-09-29.
 */
data class Comment(
        @SerializedName("postId") private var _postId: Int = 0,
        @SerializedName("id") private var _id: Int = 0,
        @SerializedName("name") private var _name: String? = null,
        @SerializedName("email") private var _email: String? = null,
        @SerializedName("body") private var _body: String? = null
) : BaseObservable() {
    var postId: Int
        @Bindable get() = _postId
        set(value) {
            _postId = value
            notifyPropertyChanged(BR.postId)
        }
    var id: Int
        @Bindable get() = _id
        set(value) {
            _id = value
            notifyPropertyChanged(BR.id)
        }

    var name: String?
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }
    var email: String?
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }
    var body: String?
        @Bindable get() = _body
        set(value) {
            _body = value
            notifyPropertyChanged(BR.body)
        }
}