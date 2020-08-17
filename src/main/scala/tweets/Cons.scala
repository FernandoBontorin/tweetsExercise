package tweets

class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
}
