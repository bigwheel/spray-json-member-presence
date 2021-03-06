# Object Member Presence Check for spray-json [![Build Status](https://travis-ci.org/bigwheel/spray-json-member-presence.svg?branch=master)](https://travis-ci.org/bigwheel/spray-json-member-presence)

## What is this ?

Default JsonReader of spray-json maps both JSON's null value and
no presense of [object member](https://tools.ietf.org/html/rfc8259#section-4) to `None`.
Therefore, we cannot determine null or no presence of member if `None` comes
(See [Sample Code](#sample-code)!).

This library introduces an type to check member presence and
`None` becomes to be used only for JSON's null.

In another words, this library could be thought as a counterpart of
[NullOptions](https://github.com/spray/spray-json#nulloptions).
NullOptions works in writing json, this library works in reading json too.

## Installation

Available from maven central.

If you use SBT you can include in your project with

```scala
libraryDependencies += "com.github.bigwheel" %% "spray-json-member-presence" % "<any-version>"
```

## Sample Code

```scala
val nullValue = """{ "a": null }""".parseJson
val noMember = """{}""".parseJson

case class CaseClassA(a: Option[Int])
object CaseClassA extends DefaultJsonProtocol {
  implicit val caseClassAFormat = jsonFormat1(CaseClassA.apply)
}

println(nullValue.convertTo[CaseClassA]) // CaseClassA(None)
println(noMember.convertTo[CaseClassA]) // CaseClassA(None)
println("Cannot distinguish 😞")

case class CaseClassB(a: MemberOption[Option[Int]])
object CaseClassB extends MemberPresenceJsonProtocol {
  implicit val caseClassBFormat = jsonFormat1(CaseClassB.apply)
}

println(nullValue.convertTo[CaseClassB]) // CaseClassB(MemberSome(None))
println(noMember.convertTo[CaseClassB]) // CaseClassB(MemberNone)
println("Now we can, yeah ☺️")
```

Complete code is [Main.scala](./src/main/scala/Main.scala).
