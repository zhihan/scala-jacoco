package me.zhihan.jacoco.internal

import org.jacoco.core.internal.flow.MethodProbesVisitor
import org.jacoco.core.internal.flow.IFrame
import org.jacoco.core.internal.flow.IProbeIdGenerator
import org.objectweb.asm.Label
/** 
  *  
  * The Jacoco internal relies on the fact that a method visitor would
  * visit the coverage nodes in the same order as they were created to
  * map the information correctly. This is done in the "Adapter"
  * layer, both the ClassProbesAdapter and MethodProbesAdapter are
  * final classes to prevent changing the behavior in subclassing. The
  * actual instrumentation or parsing the runtime data are done in the
  * "Probe visitor" objects, which the main adapter would delegate to.
  *  
  * As the logic to identify probes are all in the adatpers, it
  * ensures that the visitors will always be called in the exact same
  * order. Thus provides repeatability.
  * 
  * In the same manner, if we provide different visitors we might be able
  * to map out correspondence between probes and branch instructions. 
  */

class MyIdGenerator extends IProbeIdGenerator {
  private var id = 0
  override def nextId = {
    val r = id
    id += 1
    r
  }
}

class MethodProbeMapper extends MethodProbesVisitor {
  override def visitProbe(probeId: Int) {
    println(s"visiting probe $probeId")
  }

  override def visitJumpInsnWithProbe(opcode: Int, label:Label,
    probeId: Int, frame:IFrame) {
    println("visiting jump instrumentation with probe")
  }

  override def visitInsnWithProbe(opcode: Int, probeId: Int) {
    println("visiting instn with probe")
  }

  override def visitTableSwitchInsnWithProbes(min: Int, max:Int,
    dflt:Label, labels: Array[Label], frame: IFrame) {
    println("visiting table switch")
  }

  override def visitLookupSwitchInsnWithProbes(dflt: Label,
    keys: Array[Int], labels: Array[Label], frame: IFrame) {
    println("visiting lookup switch")
  }

}
