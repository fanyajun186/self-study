package com.example.demo.util.encrypt;

import com.lambdaworks.crypto.SCryptUtil;

/**
 * scrypt是由著名的FreeBSD黑客 Colin Percival为他的备份服务 Tarsnap开发的。
 *   设计时考虑到大规模的客制硬件攻击而刻意设计需要大量内存运算。scrypt需要使用大量内存的原因来自于产生大量伪随机性（英语：pseudorandom）资料作为算法计算的基础。
 *   一旦这些资料被产生后，算法将会以伪随机性的顺序读取这些资料产生结果。因此最直接的实做方式将会需要大量内存将这些资料储存在内存内供算法计算
 *
 */
public class ScryptUtil {

	public static void main(String[] args) {
		String originalPassword = "漫话编程";
		
		String generatedSecuredPasswordHash = SCryptUtil.scrypt(originalPassword, 16, 16, 16);
		System.out.println(generatedSecuredPasswordHash);
		
		boolean matched = SCryptUtil.check("漫话编程", generatedSecuredPasswordHash);
		System.out.println(matched);

		matched = SCryptUtil.check("漫画编程", generatedSecuredPasswordHash);
		System.out.println(matched);

	}

}
