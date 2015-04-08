package org.krzywicki.golomb.problem

import scala.collection.mutable.ArrayBuffer


trait Ruler {

  def directRepresentation: IndexedSeq[Int]

  def indirectRepresentation: IndexedSeq[Int]

  def length: Int

  def order: Int
}


case class IndirectRuler(diffs: IndexedSeq[Int]) extends Ruler {

  val indirectRepresentation = diffs
  lazy val directRepresentation = diffs.scanLeft(0)(_ + _)

  val order = indirectRepresentation.length + 1
  lazy val length = directRepresentation.last
}

case class DirectRuler(positions: IndexedSeq[Int]) extends Ruler {

  val directRepresentation = positions
  lazy val indirectRepresentation = {
    val size = positions.length
    val tmp = new ArrayBuffer[Int](size - 1)
    for (i <- 1 until size) {
      tmp(i - 1) = positions(i) - positions(i - 1)
    }
    tmp
  }

  val order = directRepresentation.length
  lazy val length = directRepresentation.last
}

