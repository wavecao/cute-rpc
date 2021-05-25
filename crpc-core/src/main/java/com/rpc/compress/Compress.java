package com.rpc.compress;

import com.rpc.extension.SPI;

/**
 * 利用SPI机制实现压缩的扩展
 * @author cao wei
 * @since 2021/04/02
 */
@SPI
public interface Compress {

    byte[] compress(byte[] bytes);


    byte[] decompress(byte[] bytes);
}
