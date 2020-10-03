import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.sousnein.lethaljokes.R
import kotlinx.android.synthetic.main.fragment_web_view.*
import java.net.URL

class WebViewFragment : Fragment() {

    private var URL = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setFragmentResultListener("requestKey") { key, bundle ->
            URL = bundle.getString("URL")!!
        }
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (URL.isEmpty())
            web_view.loadUrl("http://api.icndb.com/jokes/random/");
        else
            web_view.loadUrl(URL)
    }

}

 