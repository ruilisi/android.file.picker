package com.rls.pickfile.android.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rls.pickfile.android.R
import com.rls.pickfile.android.adapter.DirectoryAdapter
import com.rls.pickfile.android.listener.OnItemClickListener
import com.rls.pickfile.android.utils.FileUtils
import com.rls.pickfile.android.widget.EmptyRecyclerView
import java.io.File
import java.util.*

class DirectoryFragment : Fragment() {
    private var mEmptyView: View? = null
    private var mFile: File? = null
    private var mDirectoryRecyclerView: EmptyRecyclerView? = null
    private var mDirectoryAdapter: DirectoryAdapter? = null

    private var mFileClickListener: FileClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mFileClickListener = context as FileClickListener
    }

    override fun onDetach() {
        super.onDetach()
        mFileClickListener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_directory, container, false)
        mDirectoryRecyclerView = view.findViewById(R.id.directory_recycler_view)
        mEmptyView = view.findViewById(R.id.directory_empty_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgs()
        initFilesList()
    }

    private fun initFilesList() {
        mDirectoryAdapter = DirectoryAdapter(FileUtils.getFileList(mFile))
        mDirectoryAdapter!!.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                mFileClickListener?.onFileClicked(mDirectoryAdapter?.getModel(position))
            }
        })
        mDirectoryRecyclerView?.layoutManager = LinearLayoutManager(activity)
        mDirectoryRecyclerView?.adapter = mDirectoryAdapter
        mDirectoryRecyclerView?.setEmptyView(mEmptyView)
    }

    private fun initArgs() {
        if (arguments?.containsKey(ARG_FILE) == true) {
            mFile = arguments?.getSerializable(ARG_FILE) as File?
        }
    }

    companion object {
        private const val ARG_FILE = "arg_file_path"
        fun getInstance(file: File?): DirectoryFragment {
            val instance = DirectoryFragment()
            val args = Bundle()
            args.putSerializable(ARG_FILE, file)
            instance.arguments = args
            return instance
        }
    }

    internal interface FileClickListener {
        fun onFileClicked(clickedFile: File?)
    }
}