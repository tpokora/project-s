import subprocess, os

env = os.environ.copy()
env['MAVEN_HOME'] = 'C:/DEV/apache-maven-3.3.9/bin/mvn.cmd'
env['GRUNT_HOME'] = 'C:/Users/pokor/AppData/Roaming/npm/grunt.cmd'


print('Building API package...')
process = subprocess.call(env['MAVEN_HOME'] + ' package', stderr=subprocess.PIPE, env=env)
print('Building API package finished')

print('Building WEBAPP package...')
os.chdir('webapp')
process = subprocess.call(env['GRUNT_HOME'] + ' deploy-app', stderr=subprocess.PIPE, env=env)
print('Building WEBAPP package finished')