/**
 * VirtualMachine包:
 *  虚拟环境的整体
 *  - ByLineInterpreter: 逐行解释器，用以根据每一行来执行代码
 *  - Formatter: 对代码进行一定的前处理，实现代码格式化以提高运行速度
 *  - Namespace: 一个虚拟的内存空间，用以模拟python中"内存空间"的概念
 *  - VirtualMachine: 虚拟机，一个完整的Python虚拟环境
 */

package VirtualMachine;