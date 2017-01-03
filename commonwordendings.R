library(ggplot2)

ggplot(endings, aes(x=ending, y=count)) + geom_bar(stat = "identity", aes(fill=ending))
ggplot(prefixes, aes(x=prefix, y=count)) + geom_bar(stat = "identity", aes(fill=prefix))
ggplot(middle, aes(x=ending, y=count)) + geom_bar(stat = "identity", aes(fill=ending))
