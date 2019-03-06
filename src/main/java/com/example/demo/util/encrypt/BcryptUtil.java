package com.example.demo.util.encrypt;

import org.mindrot.jbcrypt.BCrypt;

/**
 * bcrypt是专门为密码存储而设计的算法，基于Blowfish加密算法变形而来
  *  实现中bcrypt会使用一个加盐的流程以防御彩虹表攻击，同时bcrypt还是适应性函数，它可以借由增加迭代之次数来抵御日益增进的计算机运算能力透过暴力法破解。
  * 由bcrypt加密的文件可在所有支持的操作系统和处理器上进行转移。它的口令必须是8至56个字符，并将在内部被转化为448位的密钥。然而，所提供的所有字符都具有十分重要的意义。密码越强大，您的数据就越安全
 *
 */
public class BcryptUtil {

	public static void main(String[] args) {
		String originalPassword ="漫画编程";
		String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
	    System.out.println(generatedSecuredPasswordHash);

	    boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);

	    System.out.println(matched);

	}

}
