package com.bunooboi.aaoj

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bunooboi.aaoj.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: MainViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var noteSurfaceView = NoteSurfaceView(requireContext(), binding.surfaceView)
        binding.surfaceView.setOnTouchListener { v, event ->
            noteSurfaceView.onTouch(event)
        }

        binding.scrollView.elevation = 2F
        binding.surfaceView.elevation = 1F
        binding.fabRedo.visibility = INVISIBLE
        binding.fabUndo.visibility = INVISIBLE
        binding.fabReset.visibility = INVISIBLE

        binding.fabChange.setOnClickListener {
            if (binding.scrollView.elevation < binding.surfaceView.elevation) {
                binding.scrollView.elevation = 2F
                binding.surfaceView.elevation = 1F
                binding.fabRedo.visibility = INVISIBLE
                binding.fabUndo.visibility = INVISIBLE
                binding.fabReset.visibility = INVISIBLE
                noteSurfaceView.invisible()
                binding.fabChange.setImageResource(R.drawable.baseline_edit_24)
            } else {
                binding.scrollView.elevation = 1F
                binding.surfaceView.elevation = 2F
                binding.fabRedo.visibility = VISIBLE
                binding.fabUndo.visibility = VISIBLE
                binding.fabReset.visibility = VISIBLE
                noteSurfaceView.visible()
                binding.fabChange.setImageResource(R.drawable.baseline_close_24)
            }
        }

        binding.fabUndo.setOnClickListener {
            noteSurfaceView.undo()
        }

        binding.fabRedo.setOnClickListener {
            noteSurfaceView.redo()
        }

        binding.fabReset.setOnClickListener {
            noteSurfaceView.reset()
        }

        viewModel.pid.observe(requireActivity()) {
            loadProblem(it.toString())
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
    }

    fun loadProblem(pid: String) {
        val queue = Volley.newRequestQueue(activity)
        val url = "https://judgeapi.u-aizu.ac.jp/resources/descriptions/ja/$pid"


        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            var htmlText =
                "<html>" + "<head><meta charset=\"utf-8\" /><style type=\"text/css\">" + "body{padding:10px;} img{width:100%;} " + "h1{color: rgb(31 41 55); text-align:center; border-bottom-width: 2px; font-size: 1.875rem; line-height: 2.25rem; } " + "h2{margin-top: 2rem; margin-bottom: 0.25rem;  color: rgb(31 41 55); text-align:left; border-bottom-width: 2px; font-size: 1.25rem; line-height: 1.75rem; } " + "p{color: rgb(31 41 55); text-align:left; margin-top: 0.5rem; margin-bottom: 0.5rem;} " + "pre{border-width: 1px; color: rgb(31 41 55); background-color: rgb(209 213 219);  border-color: rgb(204,204,204); padding: 0.5rem; white-space: pre-wrap; border-radius: 0.25rem; }" + "</style> <script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\"> </script>" + "" + "</head>"

            htmlText = htmlText + "<body>" + response.getString("html") + "</body></html>"
            htmlText = replaceDol(htmlText)
            htmlText = htmlText.replace("<br>", "").replace("#", "")
            //htmlText = htmlText.replace("[\n\r]".toRegex(), "")

            try {
                Log.v("test", htmlText)
                binding.webView.loadData(htmlText, "text/html", "utf-8")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }) { binding.webView.loadData("<div style=\"text-align:center\">That problem doesn't exist.</div>", "text/html", "utf-8") }
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