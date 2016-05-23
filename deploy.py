import subprocess
import os
import time


def separator(msg):
    print('######################################################')
    print('#')
    print('# ' + msg)
    print('#')
    print('######################################################')

env = os.environ.copy()
env['MAVEN_HOME'] = 'C:/DEV/apache-maven-3.3.9/bin/mvn.cmd'
env['GRUNT_HOME'] = 'C:/Users/pokor/AppData/Roaming/npm/grunt.cmd'

start_time = time.time()

separator('Building WEBAPP package...')

os.chdir('webapp')
process = subprocess.call(env['GRUNT_HOME'] + ' build api-prod', stderr=subprocess.PIPE, env=env)

separator('Building WEBAPP package finished')

separator('Building API package...')

os.chdir('..')
process = subprocess.call(env['MAVEN_HOME'] + ' package', stderr=subprocess.PIPE, env=env)

separator('Building API package finished')

end_time = time.time() - start_time

print('\nBuild time: %s seconds' % end_time)