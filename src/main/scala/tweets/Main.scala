package tweets

import tweets.io.DataTweetsReader

object Main extends App {
  implicit def tweetMapToEntity(
    implicit tweetMapSource: Map[String, Object]
  ) = {
    Tweet(
      tweetMapSource.getOrElse("user", new Object).toString,
      tweetMapSource.getOrElse("text", new Object).toString,
      tweetMapSource.getOrElse("retweets", new Object).toString.toDouble
    )

  }
  val allTweets = DataTweetsReader.getTweets("data/tweets")
  val tweets = allTweets
    .map(tweetList => tweetList._1 -> DataTweetsReader.toTweetSet(tweetList._2))
    .toMap
  println(tweets.get("amazondeals"))
}
