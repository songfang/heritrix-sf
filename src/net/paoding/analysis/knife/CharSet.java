/**
 * Copyright 2007 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.paoding.analysis.knife;

/**
 * 
 * @author Zhiliang Wang [qieqie.wang@gmail.com]
 * 
 * @since 1.0
 * 
 */
public class CharSet {
	
	public static boolean isArabianNumber(char ch) {
		return ch >= '0' && ch <= '9';
	}

	public static boolean isLantingLetter(char ch) {
		return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
	}

	public static boolean isCjkUnifiedIdeographs(char ch) {
		return ch >= 0x4E00 && ch < 0xA000;
	}
	
	public static boolean isBom(char ch) {
		// ref:http://www.w3.org/International/questions/qa-utf8-bom
		return ch == 0xFEFF || ch == 0xFFFE;
	}
	
	public static int toNumber(char ch) {
		switch (ch) {
		case '0':
		case '闆�':
		case '銆�':  
			return 0;
		case '1':
		case '涓�':
		case '澹�':
			return 1;
		case '2':
		case '浜�':
		case '涓�':
		case '淇�':
		case '璨�':
			return 2;
		case '3':
		case '涓�':
		case '鍙�':
			return 3;
		case '4':
		case '鍥�':
		case '鑲�':
			return 4;
		case '5':
		case '浜�':
		case '浼�':
			return 5;
		case '6':
		case '鍏�':
		case '闄�':
			return 6;
		case '7':
		case '鏌�':
		case '涓�':
			return 7;
		case '8':
		case '鎹�':
		case '鍏�':
			return 8;
		case '9':
		case '涔�':
		case '鐜�':
			return 9;
		case '鍗�':
		case '浠�':
			return 10;
		case '鐧�':
		case '浣�':
			return 100;
		case '鍗�':
		case '浠�':
			return 1000;
		/*
		 * Fix issue 12: 婧㈠嚭bug
		 */
		/*
		case '涓�':
		case '钀�':
			return 10000;
		case '浜�':
		case '鍎�':
			return 100000000;
		*/
		default:
			return -1;
		}
	}

}
