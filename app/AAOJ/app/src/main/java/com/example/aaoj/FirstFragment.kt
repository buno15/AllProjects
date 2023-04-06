package com.example.aaoj

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.aaoj.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK;
        if (Build.VERSION.SDK_INT >= 21) {
            binding.webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            binding.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            binding.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        binding.webView.setOnTouchListener(OnTouchListener { v, event -> event.action == MotionEvent.ACTION_MOVE })

        val queue = Volley.newRequestQueue(activity)
        val url = "https://judgeapi.u-aizu.ac.jp/resources/descriptions/ja/ITP1_10_D"


        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            var htmlText =
                "<html>" + "<head><meta charset=\"utf-8\" /><style type=\"text/css\">" + "body{padding:10px;} img{width:100%;} " + "h1{color: rgb(31 41 55); text-align:center; border-bottom-width: 2px; font-size: 1.875rem; line-height: 2.25rem; } " + "h2{margin-top: 2rem; margin-bottom: 0.25rem;  color: rgb(31 41 55); text-align:left; border-bottom-width: 2px; font-size: 1.25rem; line-height: 1.75rem; } " + "p{color: rgb(31 41 55); text-align:left; margin-top: 0.5rem; margin-bottom: 0.5rem;} " + "pre{border-width: 1px; color: rgb(31 41 55); background-color: rgb(209 213 219);  border-color: rgb(204,204,204); padding: 0.5rem; white-space: pre-wrap; border-radius: 0.25rem; }" + "</style> <script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\"> </script>" + "" + "</head>"

            htmlText = htmlText + "<body>" + response.getString("html") + "</body></html>"
            htmlText = replaceDol(htmlText)
            htmlText = htmlText.replace("<br>", "")
            //htmlText = htmlText.replace("[\n\r]".toRegex(), "")

            try {
                binding.webView.loadData(htmlText, "text/html", "utf-8")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }) { binding.webView.loadData("That didn't work!", "text/html", "utf-8") }
        queue.add(request)
    }

    fun replaceDol(latex: String): String {
        val sb = StringBuilder()
        var count = 0

        for (i in latex.indices) {
            if (latex[i] == '$') {
                if (count % 2 == 0) {
                    sb.append("\\(")
                } else {
                    sb.append("\\)")
                }
                count++
            } else {
                sb.append(latex[i])
            }
        }
        return sb.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}