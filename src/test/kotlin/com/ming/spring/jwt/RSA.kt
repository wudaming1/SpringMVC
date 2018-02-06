//package com.ming.spring.jwt
//
//import org.junit.Test
//import java.security.InvalidKeyException
//import java.security.NoSuchAlgorithmException
//import javax.crypto.BadPaddingException
//import javax.crypto.Cipher
//import javax.crypto.IllegalBlockSizeException
//import javax.crypto.NoSuchPaddingException
//
//class RSA {
//
//    val text = "h"
//    var encryptss = ""
//
//    @Test
//    fun generateRSA(){
//        try {
//            //必须和生成的算法类型匹配
//            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
//            cipher.init(Cipher.ENCRYPT_MODE, MyRSAKeyProvider._publicKey)
//            val encrypt = cipher.doFinal(text.toByteArray())
//            encryptss = String(encrypt)
//            println("加密后：" + encryptss)
//        } catch (e: NoSuchAlgorithmException) {
//            e.printStackTrace()
//        } catch (e: NoSuchPaddingException) {
//            e.printStackTrace()
//        } catch (e: InvalidKeyException) {
//            e.printStackTrace()
//        } catch (e: IllegalBlockSizeException) {
//            e.printStackTrace()
//        } catch (e: BadPaddingException) {
//            e.printStackTrace()
//        }
//
//        println(String(MyRSAKeyProvider._publicKey.encoded))
//    }
//
//
//    @Test
//    fun decryptText() {
//        generateRSA()
//
//        try {
//            //必须和生成的算法类型匹配
//            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
//            cipher.init(Cipher.DECRYPT_MODE, MyRSAKeyProvider._privateKey)
//            val decrypt = cipher.doFinal(encryptss.toByteArray())
//            println("解密后：" + String(decrypt))
//        } catch (e: NoSuchAlgorithmException) {
//            e.printStackTrace()
//        } catch (e: NoSuchPaddingException) {
//            e.printStackTrace()
//        } catch (e: InvalidKeyException) {
//            e.printStackTrace()
//        } catch (e: IllegalBlockSizeException) {
//            e.printStackTrace()
//        } catch (e: BadPaddingException) {
//            e.printStackTrace()
//        }
//    }
//
//
//}