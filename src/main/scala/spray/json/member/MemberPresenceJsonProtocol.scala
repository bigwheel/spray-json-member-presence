package spray.json.member

import spray.json.DefaultJsonProtocol

trait MemberPresenceJsonProtocol extends DefaultJsonProtocol with MemberPresenceFormats
object MemberPresenceJsonProtocol extends MemberPresenceJsonProtocol