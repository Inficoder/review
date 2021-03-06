package com.buaa.review.network.https;


public class Https {
    /**
     * 加密算法
     *1.对称加密
     *  加密解密使用相同秘钥  如：DES、AES-GCM、ChaCha20-Poly1305
     *2.非对称加密
     *  加密解密使用不同秘钥  如：RSA、DSA、ECDSA、 DH、ECDHE
     *3.哈希算法
     *  将任意长度的信息转为较短的固定长度的值，通常其长度要比信息小得多，且算法不可逆 如：MD5、SHA-1、SHA-2、SHA-256 等
     *
     *http：明文传输，没有身份验证，数据极易泄露、被篡改、冒充
     *为了解决上述问题
     *1.对称加密： 双发拥有相同的密钥，
     * -> 问题:(1)服务端少，客户端数量巨大，需要维护的秘钥过多，成本过高
     *        (2)密钥需要先从服务端发送到客户端，此通信过程仍为明文，还是存在原问题。
     *        @两边需要使用相同的密钥，需要使用一种安全的方式交换密钥，单纯使用对称加密，无法实现密钥交换。
     *2.非对称加密：
     * -> 服务器生成两个密钥，公钥和私钥，公钥用来加密数据，私钥用来解密数据，
     * 所有人都可以拿到公钥，私钥只有服务器自己拥有，
     * 若客户端想要与服务器进行通信，需要使用公钥对数据进行加密后发给服务器，
     * @此过程可防止数据被劫持
     *  -> 问题：(1)只能使用私钥解密，公钥加密。此时通信是单向的，如果想要双向通信，则需要客户端也生成私钥和公钥，并把公钥分发出去。
     *          (2)此时，安全性可以保证，但非对称加密的计算耗时较大，方案仍不理想
     *
     *3.使用非对称+对称加密结合
     *  -> 非对称可满足安全需求，对称可满足性能需求
     *  客户端服务端都生成私钥和密钥（用来传输对称加密的密钥（实际上是证书））
     *  此时只有通信双方拥有对称加密的密钥，所以可以使用对称加密的方式进行通信。
     *  —> 问题：(1)中间人仍然可以拿到公钥，伪造自己的公钥发送给服务器，并生成私钥，通信时使用
     * @非对称加密：只使用非对称加密是可以满足安全性要求的，但是由于非对称加密的计算耗时高于对称加密的2-3个数量级（相同安全加密级别），所以才先使用非对称交换密钥，之后再使用对称加密通信
     */
}
