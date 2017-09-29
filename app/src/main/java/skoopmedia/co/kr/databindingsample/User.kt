package skoopmedia.co.kr.databindingsample

import android.databinding.BaseObservable
import android.databinding.Bindable

/**
 * Created by MinChulKim on 2017-09-29.
 */
data class User(
        private var _firstName: String = "",
        private var _lastName: String = ""
) : BaseObservable() {

    var firstName: String
        @Bindable get() = _firstName
        set(value) {
            _firstName = value
            notifyPropertyChanged(BR.firstName)
        }

    var lastName: String
        @Bindable get() = _lastName
        set(value) {
            _lastName = value
            notifyPropertyChanged(BR.lastName)
        }
}