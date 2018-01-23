object Quaternions {

	trait Quaternion

	case class one(v: Int){
		def * (other: one): one = one(1)
		def * (other: i): i = i("i")
		def * (other: j): j = j("j")
		def * (other: k): k = k("k")
	}

	case class i(v: String){
		def * (other: one): i = i("i")
		def * (other: i): one =
		def * (other: j): one =
		def * (other: k): one =
	}
	case class j(v: String){
		def * (other: one): one = one(this.v)
		def * (other: i): one = one(this.v)
		def * (other: j): one = one(this.v)
		def * (other: k): one = one(this.v)
	}
	case class k(v: String){
		def * (other: one): one = one(this.v)
		def * (other: i): one = one(this.v)
		def * (other: j): one = one(this.v)
		def * (other: k): one = one(this.v)
	}
}
