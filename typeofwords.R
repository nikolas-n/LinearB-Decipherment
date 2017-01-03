location = c("Khania", "Knossos", "Midea", "Mycenae", "Pylos", "Thebes", "Tiryns") 
total = c(3, 1215, 0, 80, 775, 74, 3) 
unique = c(0, 1020, 0, 48, 596, 37, 1) 
anthropnyms = c(0, 687, 0, 31, 298, 16, 1) 
toponyms = c(0, 95, 0, 0, 95, 11, 0) 
df = data.frame(location, total, unique, anthropnyms, toponyms)       # df is a data frame

words <- melt(df, id = "location", measure = c("total", "unique", "anthropnyms", "toponyms"))

ggplot(words, aes(location, value, fill = variable)) + geom_bar(stat = "identity", position = "dodge")


ggplot(words, aes(x=location)) + geom_bar(stat = "identity")

ggplot(df, aes(x=location)) + geom_bar(stat = "identity", aes(y = total, fill="total")) + geom_bar(stat = "identity", aes(y = unique, fill="unique")) + geom_bar(stat = "identity", aes(y = anthropnyms, fill="anthropnyms")) + geom_bar(stat = "identity", aes(y = toponyms, fill="toponyms"))
