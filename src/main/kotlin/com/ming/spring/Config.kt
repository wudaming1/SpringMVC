package com.ming.spring

import java.io.File

object Config {

}

object SourceConfig {
    val root = "/Users/wudaming/MingSource"

    val image = "$root/images"




}

object UserConfig{
    /**
     * 获取用户的存储目录
     * @param id 用户id
     */
    private fun getUserDir(id: Int) = "${SourceConfig.root}/$id"

    /**
     * 获取用户图片的存储目录
     * @param id 用户id
     */
    fun getUserImageDir(id: Int) = "${getUserDir(id)}/images"

    /**
     * 根据扩展名生成用户头像文件名
     * @return fileName
     */
    fun generatePortraitFileName(extension:String) = "portrait.$extension"

    /**
     * 生成用户头像图片文件
     * @param id 用户id
     * @param extension 原始文件扩展名
     * @return file File
     */
    fun generatePortraitFile(id: Int,extension:String):File{
        val dir = getUserImageDir(id)
        val fileName = generatePortraitFileName(extension)
        val file = File(dir, fileName)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }
        return file
    }
}