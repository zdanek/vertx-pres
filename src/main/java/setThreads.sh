if [ "$1" == "" ]; then
	echo Unsetting number of threads. Now defaults to num of cores
	export JAVA_OPTS=""
	exit
fi

echo Setting number of threads to $1
export JAVA_OPTS="-Dvertx.pool.eventloop.size=$1"
